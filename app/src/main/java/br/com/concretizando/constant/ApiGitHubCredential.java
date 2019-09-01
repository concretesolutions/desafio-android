package br.com.concretizando.constant;

public class ApiGitHubCredential {

    public static final String URL_BASE = "https://api.github.com/";

    public static final String ENDPOINT_1 = "/search/repositories";
    public static final String ENPOINT_1_PARAM_1 = "q";
    public static final String ENPOINT_1_PARAM_2 = "sort";
    public static final String ENPOINT_1_PARAM_3 = "page";
    public static final String ENPOINT_1_PARAM_1_VALUE = "language:java";
    public static final String ENPOINT_1_PARAM_2_VALUE = "star";

    public static final String ENDPOINT_2 = "/repos/{user}/{repository}/pulls";
    public static final String ENDPOINT_2_PATH_1 = "user";
    public static final String ENDPOINT_2_PATH_2 = "repository";
}
