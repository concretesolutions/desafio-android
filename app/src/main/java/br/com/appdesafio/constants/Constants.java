package br.com.appdesafio.constants;

public class Constants {

    public static final String SERVER_INSTANCE_NAME = "";
    public static final int TIMEOUT = 60;
    public static String getBaseUrl() {
        return String.format("https://%s", SERVER_INSTANCE_NAME);
    }
}
