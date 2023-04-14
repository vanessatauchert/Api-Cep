package com.api.cep.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
@ExtendWith(MockitoExtension.class)
class ResourceExceptionHandlerTest {

    private ResourceExceptionHandler handler;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    public void setUp() {
        handler = new ResourceExceptionHandler();
        request = mock(HttpServletRequest.class);
    }

    @Test
    public void invalidCepExceptionTest() {
        MethodParameter methodParameter = mock(MethodParameter.class);
        BindingResult bindingResult = mock(BindingResult.class);
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(methodParameter, bindingResult);
        HttpServletRequest request = mock(HttpServletRequest.class);

        ResponseEntity<StandardError> response = handler.invalidCepException(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("O cep deve ser preenchido com ou sem máscara no formato de String", response.getBody().getMessage());
    }


    @Test
    @DisplayName("Deve retornar erro 404 quando o CEP informado não existir")
    public void deveRetornarErro404QuandoCepNaoExistir() {
        EnderecoNaoEncontradoException ex = new EnderecoNaoEncontradoException("CEP não encontrado");
        HttpServletRequest request = mock(HttpServletRequest.class);

        ResponseEntity<StandardError> response = handler.handleEnderecoNaoEncontradoException(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("CEP não encontrado", response.getBody().getMessage());
    }

}