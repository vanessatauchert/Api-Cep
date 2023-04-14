package com.api.cep.controller;

import com.api.cep.dto.Cep;
import com.api.cep.dto.EnderecoDTO;
import com.api.cep.exceptions.EnderecoNaoEncontradoException;
import com.api.cep.model.Endereco;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EnderecoControllerTest {

    @Mock
    private RestTemplate restTemplate;

    private static WireMockServer wireMockServer;

    @InjectMocks
    private EnderecoController enderecoController;

    @BeforeEach
    public void setup() {
        enderecoController.setRestTemplate(restTemplate);
    }

    @BeforeAll
    public static void setUp() {
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();
    }

    @AfterAll
    public static void tearDown() {
        wireMockServer.stop();
    }

    @Test
    @DisplayName("Deve retornar o endereço e o valor do frete")
    public void consultaEnderecoDeveRetornarEnderecoEFrete() {
        Cep cep = new Cep("12345678");
        Endereco endereco = new Endereco();
        endereco.setCep(cep.getCep());
        endereco.setLogradouro("Rua dos Testes");
        endereco.setBairro("Teste");
        endereco.setLocalidade("São Paulo");
        endereco.setUf("SP");

        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
        Double frete = enderecoDTO.calcularFrete();
        enderecoDTO.setFrete(frete);

        when(restTemplate.getForObject("https://viacep.com.br/ws/" + cep.getCep() + "/json", Endereco.class))
                .thenReturn(endereco);

        ResponseEntity<EnderecoDTO> response = enderecoController.consultaEndereco(cep);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(enderecoDTO);
    }

    @Test
    @DisplayName("Deve retornar erro para CEP inválido")
    public void consultaEnderecoDeveRetornarErroParaCEPInvalido() {
        Cep cep = new Cep("00000000");

        assertThrows(EnderecoNaoEncontradoException.class, () -> {
            enderecoController.consultaEndereco(cep);
        });
    }

    @Test
    @DisplayName("Deve retornar erro para endereço não encontrado")
    public void consultaEnderecoDeveRetornarErroParaEnderecoNaoEncontrado() {
        Cep cep = new Cep("12345678");

        when(restTemplate.getForObject("https://viacep.com.br/ws/" + cep.getCep() + "/json", Endereco.class))
                .thenReturn(null);

        assertThrows(EnderecoNaoEncontradoException.class, () -> {
            enderecoController.consultaEndereco(cep);
        });
    }

    @Test
    @DisplayName("Deve calcular o frete corretamente para o estado de DF")
    public void deveCalcularFreteParaEstadoDeDF() {
        Endereco endereco = new Endereco();
        endereco.setUf("DF");

        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
        Double frete = enderecoDTO.calcularFrete();

        Assertions.assertEquals(12.50, frete);
    }

    @Test
    @DisplayName("Deve calcular o frete corretamente para o estado de BA")
    public void deveCalcularFreteParaEstadoDeBA() {
        Endereco endereco = new Endereco();
        endereco.setUf("BA");

        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
        Double frete = enderecoDTO.calcularFrete();

        Assertions.assertEquals(15.98, frete);
    }

    @Test
    @DisplayName("Deve calcular o frete corretamente para o estado de SP")
    public void deveCalcularFreteParaEstadoDeSP() {
        Endereco endereco = new Endereco();
        endereco.setUf("SP");

        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
        Double frete = enderecoDTO.calcularFrete();

        Assertions.assertEquals(7.85, frete);
    }

    @Test
    @DisplayName("Deve calcular o frete corretamente para o estado de PR")
    public void deveCalcularFreteParaEstadoDePR() {
        Endereco endereco = new Endereco();
        endereco.setUf("PR");

        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
        Double frete = enderecoDTO.calcularFrete();

        Assertions.assertEquals(17.30, frete);
    }

    @Test
    @DisplayName("Deve calcular o frete corretamente para o estado de RO")
    public void deveCalcularFreteParaEstadoDeRO() {
        Endereco endereco = new Endereco();
        endereco.setUf("RO");

        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
        Double frete = enderecoDTO.calcularFrete();

        Assertions.assertEquals(20.83, frete);
    }

    @Test
    public void testConsultaEnderecoServicoIndisponivel() {
        // Configura o stub do WireMock para simular uma resposta 503 - Serviço indisponível
        stubFor(get(urlEqualTo("/ws/12345678/json"))
                .willReturn(aResponse()
                        .withStatus(503)));

        // Define o CEP a ser pesquisado
        Cep cep = new Cep("12345678");

        // Testa o método de consulta de endereço
        assertThrows(RuntimeException.class, () -> enderecoController.consultaEndereco(cep));
    }

    @Test
    public void testConsultaEnderecoRespostaInvalida() {
        // Configura o stub do WireMock para simular uma resposta inválida
        stubFor(get(urlEqualTo("/ws/12345678/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{")));

        // Define o CEP a ser pesquisado
        Cep cep = new Cep("12345678");

        // Testa o método de consulta de endereço
        assertThrows(RuntimeException.class, () -> enderecoController.consultaEndereco(cep));
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}