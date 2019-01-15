package br.com.appdesafio.constants;

public class Constants {

    public static final String SERVER_INSTANCE_NAME = "api.github.com";
    public static String getBaseUrl() {
        return String.format("https://%s", SERVER_INSTANCE_NAME);
    }
}
