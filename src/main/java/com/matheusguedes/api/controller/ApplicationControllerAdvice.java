package com.matheusguedes.api.controller;

import com.matheusguedes.api.Response;
import com.matheusguedes.exception.PedidoNaoEncontradoException;
import com.matheusguedes.exception.RegraNegocioException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handleRegraNegocioException(RegraNegocioException ex) {
        return new Response(ex.getMessage());
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(NOT_FOUND)
    public Response handlePedidoNotFoundException(PedidoNaoEncontradoException ex) {
        return new Response(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handleMethodNotValidException(MethodArgumentNotValidException ex) {
        var list = ex.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .toList();

        return new Response(list);
    }

}