package br.com.concrete.mock.response;

import br.com.concrete.model.RetornoRepositorio;

public class LoginResponse {

    public static RetornoRepositorio getLoginResnponseSucess(){
        return new RetornoRepositorio();
    }
}