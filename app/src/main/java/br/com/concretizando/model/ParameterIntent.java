package br.com.concretizando.model;

public class ParameterIntent {

    private String key;
    private String value;

    public ParameterIntent(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
