package dto;

/**
 * Created by felipe.recabarren on 20-10-18.
 */

public class RepositoryDetail {
    private String title;
    private String body;
    private String avatar;
    private String userName;
    private String nickName;
    private String urlPullRequest;

    public RepositoryDetail(String title, String body, String avatar, String userName, String nickName, String urlPullRequest) {
        this.title = title;
        this.body = body;
        this.avatar = avatar;
        this.userName = userName;
        this.nickName = nickName;
        this.urlPullRequest = urlPullRequest;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getUserName() {
        return userName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getUrlPullRequest() {
        return urlPullRequest;
    }
}
