
package com.alexandreobs.testeconcrete.model.pojo.repositories;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Item implements Parcelable {
    @Override
    public String toString() {
        return "Item{" +
                "archiveUrl='" + archiveUrl + '\'' +
                ", archived=" + archived +
                ", assigneesUrl='" + assigneesUrl + '\'' +
                ", blobsUrl='" + blobsUrl + '\'' +
                ", branchesUrl='" + branchesUrl + '\'' +
                ", cloneUrl='" + cloneUrl + '\'' +
                ", collaboratorsUrl='" + collaboratorsUrl + '\'' +
                ", commentsUrl='" + commentsUrl + '\'' +
                ", commitsUrl='" + commitsUrl + '\'' +
                ", compareUrl='" + compareUrl + '\'' +
                ", contentsUrl='" + contentsUrl + '\'' +
                ", contributorsUrl='" + contributorsUrl + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", defaultBranch='" + defaultBranch + '\'' +
                ", deploymentsUrl='" + deploymentsUrl + '\'' +
                ", description='" + description + '\'' +
                ", disabled=" + disabled +
                ", downloadsUrl='" + downloadsUrl + '\'' +
                ", eventsUrl='" + eventsUrl + '\'' +
                ", fork=" + fork +
                ", forks=" + forks +
                ", forksCount=" + forksCount +
                ", forksUrl='" + forksUrl + '\'' +
                ", fullName='" + fullName + '\'' +
                ", gitCommitsUrl='" + gitCommitsUrl + '\'' +
                ", gitRefsUrl='" + gitRefsUrl + '\'' +
                ", gitTagsUrl='" + gitTagsUrl + '\'' +
                ", gitUrl='" + gitUrl + '\'' +
                ", hasDownloads=" + hasDownloads +
                ", hasIssues=" + hasIssues +
                ", hasPages=" + hasPages +
                ", hasProjects=" + hasProjects +
                ", hasWiki=" + hasWiki +
                ", homepage='" + homepage + '\'' +
                ", hooksUrl='" + hooksUrl + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", id=" + id +
                ", issueCommentUrl='" + issueCommentUrl + '\'' +
                ", issueEventsUrl='" + issueEventsUrl + '\'' +
                ", issuesUrl='" + issuesUrl + '\'' +
                ", keysUrl='" + keysUrl + '\'' +
                ", labelsUrl='" + labelsUrl + '\'' +
                ", language='" + language + '\'' +
                ", languagesUrl='" + languagesUrl + '\'' +
                ", license=" + license +
                ", mergesUrl='" + mergesUrl + '\'' +
                ", milestonesUrl='" + milestonesUrl + '\'' +
                ", mirrorUrl=" + mirrorUrl +
                ", name='" + name + '\'' +
                ", nodeId='" + nodeId + '\'' +
                ", notificationsUrl='" + notificationsUrl + '\'' +
                ", openIssues=" + openIssues +
                ", openIssuesCount=" + openIssuesCount +
                ", owner=" + owner +
                ", privates=" + privates +
                ", pullsUrl='" + pullsUrl + '\'' +
                ", pushedAt='" + pushedAt + '\'' +
                ", releasesUrl='" + releasesUrl + '\'' +
                ", score=" + score +
                ", size=" + size +
                ", sshUrl='" + sshUrl + '\'' +
                ", stargazersCount=" + stargazersCount +
                ", stargazersUrl='" + stargazersUrl + '\'' +
                ", statusesUrl='" + statusesUrl + '\'' +
                ", subscribersUrl='" + subscribersUrl + '\'' +
                ", subscriptionUrl='" + subscriptionUrl + '\'' +
                ", svnUrl='" + svnUrl + '\'' +
                ", tagsUrl='" + tagsUrl + '\'' +
                ", teamsUrl='" + teamsUrl + '\'' +
                ", treesUrl='" + treesUrl + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", url='" + url + '\'' +
                ", watchers=" + watchers +
                ", watchersCount=" + watchersCount +
                '}';
    }

    @SerializedName("archive_url")
    private String archiveUrl;
    @Expose
    private Boolean archived;
    @SerializedName("assignees_url")
    private String assigneesUrl;
    @SerializedName("blobs_url")
    private String blobsUrl;
    @SerializedName("branches_url")
    private String branchesUrl;
    @SerializedName("clone_url")
    private String cloneUrl;
    @SerializedName("collaborators_url")
    private String collaboratorsUrl;
    @SerializedName("comments_url")
    private String commentsUrl;
    @SerializedName("commits_url")
    private String commitsUrl;
    @SerializedName("compare_url")
    private String compareUrl;
    @SerializedName("contents_url")
    private String contentsUrl;
    @SerializedName("contributors_url")
    private String contributorsUrl;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("default_branch")
    private String defaultBranch;
    @SerializedName("deployments_url")
    private String deploymentsUrl;
    @Expose
    private String description;
    @Expose
    private Boolean disabled;
    @SerializedName("downloads_url")
    private String downloadsUrl;
    @SerializedName("events_url")
    private String eventsUrl;
    @Expose
    private Boolean fork;
    @Expose
    private Long forks;
    @SerializedName("forks_count")
    private Long forksCount;
    @SerializedName("forks_url")
    private String forksUrl;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("git_commits_url")
    private String gitCommitsUrl;
    @SerializedName("git_refs_url")
    private String gitRefsUrl;
    @SerializedName("git_tags_url")
    private String gitTagsUrl;
    @SerializedName("git_url")
    private String gitUrl;
    @SerializedName("has_downloads")
    private Boolean hasDownloads;
    @SerializedName("has_issues")
    private Boolean hasIssues;
    @SerializedName("has_pages")
    private Boolean hasPages;
    @SerializedName("has_projects")
    private Boolean hasProjects;
    @SerializedName("has_wiki")
    private Boolean hasWiki;
    @Expose
    private String homepage;
    @SerializedName("hooks_url")
    private String hooksUrl;
    @SerializedName("html_url")
    private String htmlUrl;
    @Expose
    private Long id;
    @SerializedName("issue_comment_url")
    private String issueCommentUrl;
    @SerializedName("issue_events_url")
    private String issueEventsUrl;
    @SerializedName("issues_url")
    private String issuesUrl;
    @SerializedName("keys_url")
    private String keysUrl;
    @SerializedName("labels_url")
    private String labelsUrl;
    @Expose
    private String language;
    @SerializedName("languages_url")
    private String languagesUrl;
    @Expose
    private Object license;
    @SerializedName("merges_url")
    private String mergesUrl;
    @SerializedName("milestones_url")
    private String milestonesUrl;
    @SerializedName("mirror_url")
    private Object mirrorUrl;
    @Expose
    private String name;
    @SerializedName("node_id")
    private String nodeId;
    @SerializedName("notifications_url")
    private String notificationsUrl;
    @SerializedName("open_issues")
    private Long openIssues;
    @SerializedName("open_issues_count")
    private Long openIssuesCount;
    @Expose
    private Owner owner;
    @Expose
    private Boolean privates;
    @SerializedName("pulls_url")
    private String pullsUrl;
    @SerializedName("pushed_at")
    private String pushedAt;
    @SerializedName("releases_url")
    private String releasesUrl;
    @Expose
    private Double score;
    @Expose
    private Long size;
    @SerializedName("ssh_url")
    private String sshUrl;
    @SerializedName("stargazers_count")
    private Long stargazersCount;
    @SerializedName("stargazers_url")
    private String stargazersUrl;
    @SerializedName("statuses_url")
    private String statusesUrl;
    @SerializedName("subscribers_url")
    private String subscribersUrl;
    @SerializedName("subscription_url")
    private String subscriptionUrl;
    @SerializedName("svn_url")
    private String svnUrl;
    @SerializedName("tags_url")
    private String tagsUrl;
    @SerializedName("teams_url")
    private String teamsUrl;
    @SerializedName("trees_url")
    private String treesUrl;
    @SerializedName("updated_at")
    private String updatedAt;
    @Expose
    private String url;
    @Expose
    private Long watchers;
    @SerializedName("watchers_count")
    private Long watchersCount;

    protected Item(Parcel in) {
        archiveUrl = in.readString();
        byte tmpArchived = in.readByte();
        archived = tmpArchived == 0 ? null : tmpArchived == 1;
        assigneesUrl = in.readString();
        blobsUrl = in.readString();
        branchesUrl = in.readString();
        cloneUrl = in.readString();
        collaboratorsUrl = in.readString();
        commentsUrl = in.readString();
        commitsUrl = in.readString();
        compareUrl = in.readString();
        contentsUrl = in.readString();
        contributorsUrl = in.readString();
        createdAt = in.readString();
        defaultBranch = in.readString();
        deploymentsUrl = in.readString();
        description = in.readString();
        byte tmpDisabled = in.readByte();
        disabled = tmpDisabled == 0 ? null : tmpDisabled == 1;
        downloadsUrl = in.readString();
        eventsUrl = in.readString();
        byte tmpFork = in.readByte();
        fork = tmpFork == 0 ? null : tmpFork == 1;
        if (in.readByte() == 0) {
            forks = null;
        } else {
            forks = in.readLong();
        }
        if (in.readByte() == 0) {
            forksCount = null;
        } else {
            forksCount = in.readLong();
        }
        forksUrl = in.readString();
        fullName = in.readString();
        gitCommitsUrl = in.readString();
        gitRefsUrl = in.readString();
        gitTagsUrl = in.readString();
        gitUrl = in.readString();
        byte tmpHasDownloads = in.readByte();
        hasDownloads = tmpHasDownloads == 0 ? null : tmpHasDownloads == 1;
        byte tmpHasIssues = in.readByte();
        hasIssues = tmpHasIssues == 0 ? null : tmpHasIssues == 1;
        byte tmpHasPages = in.readByte();
        hasPages = tmpHasPages == 0 ? null : tmpHasPages == 1;
        byte tmpHasProjects = in.readByte();
        hasProjects = tmpHasProjects == 0 ? null : tmpHasProjects == 1;
        byte tmpHasWiki = in.readByte();
        hasWiki = tmpHasWiki == 0 ? null : tmpHasWiki == 1;
        homepage = in.readString();
        hooksUrl = in.readString();
        htmlUrl = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        issueCommentUrl = in.readString();
        issueEventsUrl = in.readString();
        issuesUrl = in.readString();
        keysUrl = in.readString();
        labelsUrl = in.readString();
        language = in.readString();
        languagesUrl = in.readString();
        mergesUrl = in.readString();
        milestonesUrl = in.readString();
        name = in.readString();
        nodeId = in.readString();
        notificationsUrl = in.readString();
        if (in.readByte() == 0) {
            openIssues = null;
        } else {
            openIssues = in.readLong();
        }
        if (in.readByte() == 0) {
            openIssuesCount = null;
        } else {
            openIssuesCount = in.readLong();
        }
        byte tmpPrivates = in.readByte();
        privates = tmpPrivates == 0 ? null : tmpPrivates == 1;
        pullsUrl = in.readString();
        pushedAt = in.readString();
        releasesUrl = in.readString();
        if (in.readByte() == 0) {
            score = null;
        } else {
            score = in.readDouble();
        }
        if (in.readByte() == 0) {
            size = null;
        } else {
            size = in.readLong();
        }
        sshUrl = in.readString();
        if (in.readByte() == 0) {
            stargazersCount = null;
        } else {
            stargazersCount = in.readLong();
        }
        stargazersUrl = in.readString();
        statusesUrl = in.readString();
        subscribersUrl = in.readString();
        subscriptionUrl = in.readString();
        svnUrl = in.readString();
        tagsUrl = in.readString();
        teamsUrl = in.readString();
        treesUrl = in.readString();
        updatedAt = in.readString();
        url = in.readString();
        if (in.readByte() == 0) {
            watchers = null;
        } else {
            watchers = in.readLong();
        }
        if (in.readByte() == 0) {
            watchersCount = null;
        } else {
            watchersCount = in.readLong();
        }
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getArchiveUrl() {
        return archiveUrl;
    }

    public void setArchiveUrl(String archiveUrl) {
        this.archiveUrl = archiveUrl;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getAssigneesUrl() {
        return assigneesUrl;
    }

    public void setAssigneesUrl(String assigneesUrl) {
        this.assigneesUrl = assigneesUrl;
    }

    public String getBlobsUrl() {
        return blobsUrl;
    }

    public void setBlobsUrl(String blobsUrl) {
        this.blobsUrl = blobsUrl;
    }

    public String getBranchesUrl() {
        return branchesUrl;
    }

    public void setBranchesUrl(String branchesUrl) {
        this.branchesUrl = branchesUrl;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    public String getCollaboratorsUrl() {
        return collaboratorsUrl;
    }

    public void setCollaboratorsUrl(String collaboratorsUrl) {
        this.collaboratorsUrl = collaboratorsUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public String getCommitsUrl() {
        return commitsUrl;
    }

    public void setCommitsUrl(String commitsUrl) {
        this.commitsUrl = commitsUrl;
    }

    public String getCompareUrl() {
        return compareUrl;
    }

    public void setCompareUrl(String compareUrl) {
        this.compareUrl = compareUrl;
    }

    public String getContentsUrl() {
        return contentsUrl;
    }

    public void setContentsUrl(String contentsUrl) {
        this.contentsUrl = contentsUrl;
    }

    public String getContributorsUrl() {
        return contributorsUrl;
    }

    public void setContributorsUrl(String contributorsUrl) {
        this.contributorsUrl = contributorsUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    public String getDeploymentsUrl() {
        return deploymentsUrl;
    }

    public void setDeploymentsUrl(String deploymentsUrl) {
        this.deploymentsUrl = deploymentsUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getDownloadsUrl() {
        return downloadsUrl;
    }

    public void setDownloadsUrl(String downloadsUrl) {
        this.downloadsUrl = downloadsUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public Boolean getFork() {
        return fork;
    }

    public void setFork(Boolean fork) {
        this.fork = fork;
    }

    public Long getForks() {
        return forks;
    }

    public void setForks(Long forks) {
        this.forks = forks;
    }

    public Long getForksCount() {
        return forksCount;
    }

    public void setForksCount(Long forksCount) {
        this.forksCount = forksCount;
    }

    public String getForksUrl() {
        return forksUrl;
    }

    public void setForksUrl(String forksUrl) {
        this.forksUrl = forksUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGitCommitsUrl() {
        return gitCommitsUrl;
    }

    public void setGitCommitsUrl(String gitCommitsUrl) {
        this.gitCommitsUrl = gitCommitsUrl;
    }

    public String getGitRefsUrl() {
        return gitRefsUrl;
    }

    public void setGitRefsUrl(String gitRefsUrl) {
        this.gitRefsUrl = gitRefsUrl;
    }

    public String getGitTagsUrl() {
        return gitTagsUrl;
    }

    public void setGitTagsUrl(String gitTagsUrl) {
        this.gitTagsUrl = gitTagsUrl;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public Boolean getHasDownloads() {
        return hasDownloads;
    }

    public void setHasDownloads(Boolean hasDownloads) {
        this.hasDownloads = hasDownloads;
    }

    public Boolean getHasIssues() {
        return hasIssues;
    }

    public void setHasIssues(Boolean hasIssues) {
        this.hasIssues = hasIssues;
    }

    public Boolean getHasPages() {
        return hasPages;
    }

    public void setHasPages(Boolean hasPages) {
        this.hasPages = hasPages;
    }

    public Boolean getHasProjects() {
        return hasProjects;
    }

    public void setHasProjects(Boolean hasProjects) {
        this.hasProjects = hasProjects;
    }

    public Boolean getHasWiki() {
        return hasWiki;
    }

    public void setHasWiki(Boolean hasWiki) {
        this.hasWiki = hasWiki;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getHooksUrl() {
        return hooksUrl;
    }

    public void setHooksUrl(String hooksUrl) {
        this.hooksUrl = hooksUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIssueCommentUrl() {
        return issueCommentUrl;
    }

    public void setIssueCommentUrl(String issueCommentUrl) {
        this.issueCommentUrl = issueCommentUrl;
    }

    public String getIssueEventsUrl() {
        return issueEventsUrl;
    }

    public void setIssueEventsUrl(String issueEventsUrl) {
        this.issueEventsUrl = issueEventsUrl;
    }

    public String getIssuesUrl() {
        return issuesUrl;
    }

    public void setIssuesUrl(String issuesUrl) {
        this.issuesUrl = issuesUrl;
    }

    public String getKeysUrl() {
        return keysUrl;
    }

    public void setKeysUrl(String keysUrl) {
        this.keysUrl = keysUrl;
    }

    public String getLabelsUrl() {
        return labelsUrl;
    }

    public void setLabelsUrl(String labelsUrl) {
        this.labelsUrl = labelsUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguagesUrl() {
        return languagesUrl;
    }

    public void setLanguagesUrl(String languagesUrl) {
        this.languagesUrl = languagesUrl;
    }

    public Object getLicense() {
        return license;
    }

    public void setLicense(Object license) {
        this.license = license;
    }

    public String getMergesUrl() {
        return mergesUrl;
    }

    public void setMergesUrl(String mergesUrl) {
        this.mergesUrl = mergesUrl;
    }

    public String getMilestonesUrl() {
        return milestonesUrl;
    }

    public void setMilestonesUrl(String milestonesUrl) {
        this.milestonesUrl = milestonesUrl;
    }

    public Object getMirrorUrl() {
        return mirrorUrl;
    }

    public void setMirrorUrl(Object mirrorUrl) {
        this.mirrorUrl = mirrorUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNotificationsUrl() {
        return notificationsUrl;
    }

    public void setNotificationsUrl(String notificationsUrl) {
        this.notificationsUrl = notificationsUrl;
    }

    public Long getOpenIssues() {
        return openIssues;
    }

    public void setOpenIssues(Long openIssues) {
        this.openIssues = openIssues;
    }

    public Long getOpenIssuesCount() {
        return openIssuesCount;
    }

    public void setOpenIssuesCount(Long openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Boolean getPrivate() {
        return privates;
    }

    public void setPrivate(Boolean privates) {
        this.privates = privates;
    }

    public String getPullsUrl() {
        return pullsUrl;
    }

    public void setPullsUrl(String pullsUrl) {
        this.pullsUrl = pullsUrl;
    }

    public String getPushedAt() {
        return pushedAt;
    }

    public void setPushedAt(String pushedAt) {
        this.pushedAt = pushedAt;
    }

    public String getReleasesUrl() {
        return releasesUrl;
    }

    public void setReleasesUrl(String releasesUrl) {
        this.releasesUrl = releasesUrl;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getSshUrl() {
        return sshUrl;
    }

    public void setSshUrl(String sshUrl) {
        this.sshUrl = sshUrl;
    }

    public Long getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(Long stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public String getStargazersUrl() {
        return stargazersUrl;
    }

    public void setStargazersUrl(String stargazersUrl) {
        this.stargazersUrl = stargazersUrl;
    }

    public String getStatusesUrl() {
        return statusesUrl;
    }

    public void setStatusesUrl(String statusesUrl) {
        this.statusesUrl = statusesUrl;
    }

    public String getSubscribersUrl() {
        return subscribersUrl;
    }

    public void setSubscribersUrl(String subscribersUrl) {
        this.subscribersUrl = subscribersUrl;
    }

    public String getSubscriptionUrl() {
        return subscriptionUrl;
    }

    public void setSubscriptionUrl(String subscriptionUrl) {
        this.subscriptionUrl = subscriptionUrl;
    }

    public String getSvnUrl() {
        return svnUrl;
    }

    public void setSvnUrl(String svnUrl) {
        this.svnUrl = svnUrl;
    }

    public String getTagsUrl() {
        return tagsUrl;
    }

    public void setTagsUrl(String tagsUrl) {
        this.tagsUrl = tagsUrl;
    }

    public String getTeamsUrl() {
        return teamsUrl;
    }

    public void setTeamsUrl(String teamsUrl) {
        this.teamsUrl = teamsUrl;
    }

    public String getTreesUrl() {
        return treesUrl;
    }

    public void setTreesUrl(String treesUrl) {
        this.treesUrl = treesUrl;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getWatchers() {
        return watchers;
    }

    public void setWatchers(Long watchers) {
        this.watchers = watchers;
    }

    public Long getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(Long watchersCount) {
        this.watchersCount = watchersCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(archiveUrl);
        parcel.writeByte((byte) (archived == null ? 0 : archived ? 1 : 2));
        parcel.writeString(assigneesUrl);
        parcel.writeString(blobsUrl);
        parcel.writeString(branchesUrl);
        parcel.writeString(cloneUrl);
        parcel.writeString(collaboratorsUrl);
        parcel.writeString(commentsUrl);
        parcel.writeString(commitsUrl);
        parcel.writeString(compareUrl);
        parcel.writeString(contentsUrl);
        parcel.writeString(contributorsUrl);
        parcel.writeString(createdAt);
        parcel.writeString(defaultBranch);
        parcel.writeString(deploymentsUrl);
        parcel.writeString(description);
        parcel.writeByte((byte) (disabled == null ? 0 : disabled ? 1 : 2));
        parcel.writeString(downloadsUrl);
        parcel.writeString(eventsUrl);
        parcel.writeByte((byte) (fork == null ? 0 : fork ? 1 : 2));
        if (forks == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(forks);
        }
        if (forksCount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(forksCount);
        }
        parcel.writeString(forksUrl);
        parcel.writeString(fullName);
        parcel.writeString(gitCommitsUrl);
        parcel.writeString(gitRefsUrl);
        parcel.writeString(gitTagsUrl);
        parcel.writeString(gitUrl);
        parcel.writeByte((byte) (hasDownloads == null ? 0 : hasDownloads ? 1 : 2));
        parcel.writeByte((byte) (hasIssues == null ? 0 : hasIssues ? 1 : 2));
        parcel.writeByte((byte) (hasPages == null ? 0 : hasPages ? 1 : 2));
        parcel.writeByte((byte) (hasProjects == null ? 0 : hasProjects ? 1 : 2));
        parcel.writeByte((byte) (hasWiki == null ? 0 : hasWiki ? 1 : 2));
        parcel.writeString(homepage);
        parcel.writeString(hooksUrl);
        parcel.writeString(htmlUrl);
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(issueCommentUrl);
        parcel.writeString(issueEventsUrl);
        parcel.writeString(issuesUrl);
        parcel.writeString(keysUrl);
        parcel.writeString(labelsUrl);
        parcel.writeString(language);
        parcel.writeString(languagesUrl);
        parcel.writeString(mergesUrl);
        parcel.writeString(milestonesUrl);
        parcel.writeString(name);
        parcel.writeString(nodeId);
        parcel.writeString(notificationsUrl);
        if (openIssues == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(openIssues);
        }
        if (openIssuesCount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(openIssuesCount);
        }
        parcel.writeByte((byte) (privates == null ? 0 : privates ? 1 : 2));
        parcel.writeString(pullsUrl);
        parcel.writeString(pushedAt);
        parcel.writeString(releasesUrl);
        if (score == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(score);
        }
        if (size == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(size);
        }
        parcel.writeString(sshUrl);
        if (stargazersCount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(stargazersCount);
        }
        parcel.writeString(stargazersUrl);
        parcel.writeString(statusesUrl);
        parcel.writeString(subscribersUrl);
        parcel.writeString(subscriptionUrl);
        parcel.writeString(svnUrl);
        parcel.writeString(tagsUrl);
        parcel.writeString(teamsUrl);
        parcel.writeString(treesUrl);
        parcel.writeString(updatedAt);
        parcel.writeString(url);
        if (watchers == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(watchers);
        }
        if (watchersCount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(watchersCount);
        }
    }
}
