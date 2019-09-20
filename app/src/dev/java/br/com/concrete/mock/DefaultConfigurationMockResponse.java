package br.com.concrete.mock;

import java.util.HashMap;
import br.com.concrete.mock.response.LoginResponse;
import br.com.concrete.util.Constantes;

public class DefaultConfigurationMockResponse {

    private HashMap<String, Object> mapResponse;

    public DefaultConfigurationMockResponse() {
        mapResponse = new HashMap<>();
        createResponse();
    }

    public Object response(String URI) {
        return mapResponse.get(URI);
    }

    private void createResponse() {
        mapResponse.put(Constantes.URL_REPOSITORIES, LoginResponse.getLoginResnponseSucess());
    }
}