package com.api.cep.controller;

import com.api.cep.dto.Cep;
import com.api.cep.dto.EnderecoDTO;
import com.api.cep.exceptions.EnderecoNaoEncontradoException;
import com.api.cep.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EnderecoControllerTest {

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        restTemplate = Mockito.mock(RestTemplate.class);
    }

    @Test
    void testConsultaEnderecoValido() {
        // cria um objeto Endereco
        Endereco endereco = new Endereco();
        endereco.setCep("12345-678");
        endereco.setLogradouro("Rua das Flores");
        endereco.setComplemento("Apto 123");
        endereco.setBairro("Centro");
        endereco.setLocalidade("São Paulo");
        endereco.setUf("SP");

        // define o comportamento do mock do RestTemplate
        when(restTemplate.getForObject("https://viacep.com.br/ws/12345-678/json", Endereco.class))
                .thenReturn(endereco);

        // chama o método consultaEndereco() do controller
        EnderecoController controller = new EnderecoController();
        controller.setRestTemplate(restTemplate);
        Cep cep = new Cep();
        cep.setCep("12345-678");
        ResponseEntity<EnderecoDTO> response = controller.consultaEndereco(cep);

        // verifica se o retorno é o esperado
        assertEquals("12345-678", response.getBody().getCep());
        assertEquals("Rua das Flores", response.getBody().getRua());
        assertEquals("Apto 123", response.getBody().getComplemento());
        assertEquals("Centro", response.getBody().getBairro());
        assertEquals("São Paulo", response.getBody().getCidade());
        assertEquals("SP", response.getBody().getEstado());
    }

    @Test
    void testCepNaoEncontrado(){
        // cria um objeto Cep
        Cep cep = new Cep();
        cep.setCep("000000-000");

        // define o comportamento do mock do RestTemplate para lançar uma exceção
        when(restTemplate.getForObject("https://viacep.com.br/ws/000000-000/json", Endereco.class))
                .thenThrow(new EnderecoNaoEncontradoException("Serviço indisponível"));

        // chama o método consultaEndereco() do controller
        EnderecoController controller = new EnderecoController();
        controller.setRestTemplate(restTemplate);

        // verifica se o método lança uma exceção EnderecoServicoIndisponivelException
        assertThrows(EnderecoNaoEncontradoException.class, () -> {
            controller.consultaEndereco(cep);
        });
    }
}