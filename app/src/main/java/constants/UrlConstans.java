package constants;

/**
 * Created by felipe.recabarren on 20-10-18.
 */

public class UrlConstans {
    private String reposityListUrl = "https://api.github.com/search/repositories?q=language:Java&sort=stars&page=";
    private String repositoryDetailUrl = "https://api.github.com/repos/";
    public UrlConstans() {
    }

    public String getReposityListUrl(int page) {
        return reposityListUrl+page;
    }

    public String getRepositoryDetailUrl(String creator,String repository){
        return repositoryDetailUrl+creator+"/"+repository+"/pulls";
    }
}
