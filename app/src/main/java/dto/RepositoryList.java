package dto;

/**
 * Created by felipe.recabarren on 20-10-18.
 */

public class RepositoryList {
    private String repositoryName;
    private String repositoryDescription;
    private String branch;
    private String favorite;
    private String avatar;
    private String userName;
    private String nickName;
    private String opened;
    private String closed;

    public RepositoryList(String repositoryName, String repositoryDescription, String branch, String favorite, String avatar, String userName, String nickName, String opened, String closed) {
        this.repositoryName = repositoryName;
        this.repositoryDescription = repositoryDescription;
        this.branch = branch;
        this.favorite = favorite;
        this.avatar = avatar;
        this.userName = userName;
        this.nickName = nickName;
        this.opened = opened;
        this.closed = closed;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public String getRepositoryDescription() {
        return repositoryDescription;
    }

    public String getBranch() {
        return branch;
    }

    public String getFavorite() {
        return favorite;
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

    public String getOpened() {
        return opened;
    }

    public String getClosed() {
        return closed;
    }
}
