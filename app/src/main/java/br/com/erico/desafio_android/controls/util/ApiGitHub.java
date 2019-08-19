package br.com.erico.desafio_android.controls.util;

import br.com.erico.desafio_android.controls.interfaces.Services;

public class ApiGitHub {

    public static final String BASE_URL = "https://api.github.com";

    public static Services getGitService() {
        return RetroClient.getClient(BASE_URL).create(Services.class);
    }

}
