package com.example.github_api_concrete.model.pojo.pullrequests;

import com.google.gson.annotations.SerializedName;

public class Repo{

	@SerializedName("stargazers_count")
	private int stargazersCount;

	@SerializedName("pushed_at")
	private String pushedAt;

	@SerializedName("subscription_url")
	private String subscriptionUrl;

	@SerializedName("language")
	private Object language;

	@SerializedName("branches_url")
	private String branchesUrl;

	@SerializedName("issue_comment_url")
	private String issueCommentUrl;

	@SerializedName("labels_url")
	private String labelsUrl;

	@SerializedName("subscribers_url")
	private String subscribersUrl;

	@SerializedName("releases_url")
	private String releasesUrl;

	@SerializedName("svn_url")
	private String svnUrl;

	@SerializedName("id")
	private int id;

	@SerializedName("forks")
	private int forks;

	@SerializedName("archive_url")
	private String archiveUrl;

	@SerializedName("git_refs_url")
	private String gitRefsUrl;

	@SerializedName("forks_url")
	private String forksUrl;

	@SerializedName("statuses_url")
	private String statusesUrl;

	@SerializedName("ssh_url")
	private String sshUrl;

	@SerializedName("license")
	private Object license;

	@SerializedName("full_name")
	private String fullName;

	@SerializedName("size")
	private int size;

	@SerializedName("languages_url")
	private String languagesUrl;

	@SerializedName("html_url")
	private String htmlUrl;

	@SerializedName("collaborators_url")
	private String collaboratorsUrl;

	@SerializedName("clone_url")
	private String cloneUrl;

	@SerializedName("name")
	private String name;

	@SerializedName("pulls_url")
	private String pullsUrl;

	@SerializedName("default_branch")
	private String defaultBranch;

	@SerializedName("hooks_url")
	private String hooksUrl;

	@SerializedName("trees_url")
	private String treesUrl;

	@SerializedName("tags_url")
	private String tagsUrl;

	@SerializedName("private")
	private boolean jsonMemberPrivate;

	@SerializedName("contributors_url")
	private String contributorsUrl;

	@SerializedName("has_downloads")
	private boolean hasDownloads;

	@SerializedName("notifications_url")
	private String notificationsUrl;

	@SerializedName("open_issues_count")
	private int openIssuesCount;

	@SerializedName("description")
	private String description;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("watchers")
	private int watchers;

	@SerializedName("keys_url")
	private String keysUrl;

	@SerializedName("deployments_url")
	private String deploymentsUrl;

	@SerializedName("has_projects")
	private boolean hasProjects;

	@SerializedName("archived")
	private boolean archived;

	@SerializedName("has_wiki")
	private boolean hasWiki;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("comments_url")
	private String commentsUrl;

	@SerializedName("stargazers_url")
	private String stargazersUrl;

	@SerializedName("disabled")
	private boolean disabled;

	@SerializedName("git_url")
	private String gitUrl;

	@SerializedName("has_pages")
	private boolean hasPages;

	@SerializedName("owner")
	private Owner owner;

	@SerializedName("commits_url")
	private String commitsUrl;

	@SerializedName("compare_url")
	private String compareUrl;

	@SerializedName("git_commits_url")
	private String gitCommitsUrl;

	@SerializedName("blobs_url")
	private String blobsUrl;

	@SerializedName("git_tags_url")
	private String gitTagsUrl;

	@SerializedName("merges_url")
	private String mergesUrl;

	@SerializedName("downloads_url")
	private String downloadsUrl;

	@SerializedName("has_issues")
	private boolean hasIssues;

	@SerializedName("url")
	private String url;

	@SerializedName("contents_url")
	private String contentsUrl;

	@SerializedName("mirror_url")
	private Object mirrorUrl;

	@SerializedName("milestones_url")
	private String milestonesUrl;

	@SerializedName("teams_url")
	private String teamsUrl;

	@SerializedName("fork")
	private boolean fork;

	@SerializedName("issues_url")
	private String issuesUrl;

	@SerializedName("events_url")
	private String eventsUrl;

	@SerializedName("issue_events_url")
	private String issueEventsUrl;

	@SerializedName("assignees_url")
	private String assigneesUrl;

	@SerializedName("open_issues")
	private int openIssues;

	@SerializedName("watchers_count")
	private int watchersCount;

	@SerializedName("node_id")
	private String nodeId;

	@SerializedName("homepage")
	private String homepage;

	@SerializedName("forks_count")
	private int forksCount;

	public void setStargazersCount(int stargazersCount){
		this.stargazersCount = stargazersCount;
	}

	public int getStargazersCount(){
		return stargazersCount;
	}

	public void setPushedAt(String pushedAt){
		this.pushedAt = pushedAt;
	}

	public String getPushedAt(){
		return pushedAt;
	}

	public void setSubscriptionUrl(String subscriptionUrl){
		this.subscriptionUrl = subscriptionUrl;
	}

	public String getSubscriptionUrl(){
		return subscriptionUrl;
	}

	public void setLanguage(Object language){
		this.language = language;
	}

	public Object getLanguage(){
		return language;
	}

	public void setBranchesUrl(String branchesUrl){
		this.branchesUrl = branchesUrl;
	}

	public String getBranchesUrl(){
		return branchesUrl;
	}

	public void setIssueCommentUrl(String issueCommentUrl){
		this.issueCommentUrl = issueCommentUrl;
	}

	public String getIssueCommentUrl(){
		return issueCommentUrl;
	}

	public void setLabelsUrl(String labelsUrl){
		this.labelsUrl = labelsUrl;
	}

	public String getLabelsUrl(){
		return labelsUrl;
	}

	public void setSubscribersUrl(String subscribersUrl){
		this.subscribersUrl = subscribersUrl;
	}

	public String getSubscribersUrl(){
		return subscribersUrl;
	}

	public void setReleasesUrl(String releasesUrl){
		this.releasesUrl = releasesUrl;
	}

	public String getReleasesUrl(){
		return releasesUrl;
	}

	public void setSvnUrl(String svnUrl){
		this.svnUrl = svnUrl;
	}

	public String getSvnUrl(){
		return svnUrl;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setForks(int forks){
		this.forks = forks;
	}

	public int getForks(){
		return forks;
	}

	public void setArchiveUrl(String archiveUrl){
		this.archiveUrl = archiveUrl;
	}

	public String getArchiveUrl(){
		return archiveUrl;
	}

	public void setGitRefsUrl(String gitRefsUrl){
		this.gitRefsUrl = gitRefsUrl;
	}

	public String getGitRefsUrl(){
		return gitRefsUrl;
	}

	public void setForksUrl(String forksUrl){
		this.forksUrl = forksUrl;
	}

	public String getForksUrl(){
		return forksUrl;
	}

	public void setStatusesUrl(String statusesUrl){
		this.statusesUrl = statusesUrl;
	}

	public String getStatusesUrl(){
		return statusesUrl;
	}

	public void setSshUrl(String sshUrl){
		this.sshUrl = sshUrl;
	}

	public String getSshUrl(){
		return sshUrl;
	}

	public void setLicense(Object license){
		this.license = license;
	}

	public Object getLicense(){
		return license;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	public String getFullName(){
		return fullName;
	}

	public void setSize(int size){
		this.size = size;
	}

	public int getSize(){
		return size;
	}

	public void setLanguagesUrl(String languagesUrl){
		this.languagesUrl = languagesUrl;
	}

	public String getLanguagesUrl(){
		return languagesUrl;
	}

	public void setHtmlUrl(String htmlUrl){
		this.htmlUrl = htmlUrl;
	}

	public String getHtmlUrl(){
		return htmlUrl;
	}

	public void setCollaboratorsUrl(String collaboratorsUrl){
		this.collaboratorsUrl = collaboratorsUrl;
	}

	public String getCollaboratorsUrl(){
		return collaboratorsUrl;
	}

	public void setCloneUrl(String cloneUrl){
		this.cloneUrl = cloneUrl;
	}

	public String getCloneUrl(){
		return cloneUrl;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPullsUrl(String pullsUrl){
		this.pullsUrl = pullsUrl;
	}

	public String getPullsUrl(){
		return pullsUrl;
	}

	public void setDefaultBranch(String defaultBranch){
		this.defaultBranch = defaultBranch;
	}

	public String getDefaultBranch(){
		return defaultBranch;
	}

	public void setHooksUrl(String hooksUrl){
		this.hooksUrl = hooksUrl;
	}

	public String getHooksUrl(){
		return hooksUrl;
	}

	public void setTreesUrl(String treesUrl){
		this.treesUrl = treesUrl;
	}

	public String getTreesUrl(){
		return treesUrl;
	}

	public void setTagsUrl(String tagsUrl){
		this.tagsUrl = tagsUrl;
	}

	public String getTagsUrl(){
		return tagsUrl;
	}

	public void setJsonMemberPrivate(boolean jsonMemberPrivate){
		this.jsonMemberPrivate = jsonMemberPrivate;
	}

	public boolean isJsonMemberPrivate(){
		return jsonMemberPrivate;
	}

	public void setContributorsUrl(String contributorsUrl){
		this.contributorsUrl = contributorsUrl;
	}

	public String getContributorsUrl(){
		return contributorsUrl;
	}

	public void setHasDownloads(boolean hasDownloads){
		this.hasDownloads = hasDownloads;
	}

	public boolean isHasDownloads(){
		return hasDownloads;
	}

	public void setNotificationsUrl(String notificationsUrl){
		this.notificationsUrl = notificationsUrl;
	}

	public String getNotificationsUrl(){
		return notificationsUrl;
	}

	public void setOpenIssuesCount(int openIssuesCount){
		this.openIssuesCount = openIssuesCount;
	}

	public int getOpenIssuesCount(){
		return openIssuesCount;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setWatchers(int watchers){
		this.watchers = watchers;
	}

	public int getWatchers(){
		return watchers;
	}

	public void setKeysUrl(String keysUrl){
		this.keysUrl = keysUrl;
	}

	public String getKeysUrl(){
		return keysUrl;
	}

	public void setDeploymentsUrl(String deploymentsUrl){
		this.deploymentsUrl = deploymentsUrl;
	}

	public String getDeploymentsUrl(){
		return deploymentsUrl;
	}

	public void setHasProjects(boolean hasProjects){
		this.hasProjects = hasProjects;
	}

	public boolean isHasProjects(){
		return hasProjects;
	}

	public void setArchived(boolean archived){
		this.archived = archived;
	}

	public boolean isArchived(){
		return archived;
	}

	public void setHasWiki(boolean hasWiki){
		this.hasWiki = hasWiki;
	}

	public boolean isHasWiki(){
		return hasWiki;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setCommentsUrl(String commentsUrl){
		this.commentsUrl = commentsUrl;
	}

	public String getCommentsUrl(){
		return commentsUrl;
	}

	public void setStargazersUrl(String stargazersUrl){
		this.stargazersUrl = stargazersUrl;
	}

	public String getStargazersUrl(){
		return stargazersUrl;
	}

	public void setDisabled(boolean disabled){
		this.disabled = disabled;
	}

	public boolean isDisabled(){
		return disabled;
	}

	public void setGitUrl(String gitUrl){
		this.gitUrl = gitUrl;
	}

	public String getGitUrl(){
		return gitUrl;
	}

	public void setHasPages(boolean hasPages){
		this.hasPages = hasPages;
	}

	public boolean isHasPages(){
		return hasPages;
	}

	public void setOwner(Owner owner){
		this.owner = owner;
	}

	public Owner getOwner(){
		return owner;
	}

	public void setCommitsUrl(String commitsUrl){
		this.commitsUrl = commitsUrl;
	}

	public String getCommitsUrl(){
		return commitsUrl;
	}

	public void setCompareUrl(String compareUrl){
		this.compareUrl = compareUrl;
	}

	public String getCompareUrl(){
		return compareUrl;
	}

	public void setGitCommitsUrl(String gitCommitsUrl){
		this.gitCommitsUrl = gitCommitsUrl;
	}

	public String getGitCommitsUrl(){
		return gitCommitsUrl;
	}

	public void setBlobsUrl(String blobsUrl){
		this.blobsUrl = blobsUrl;
	}

	public String getBlobsUrl(){
		return blobsUrl;
	}

	public void setGitTagsUrl(String gitTagsUrl){
		this.gitTagsUrl = gitTagsUrl;
	}

	public String getGitTagsUrl(){
		return gitTagsUrl;
	}

	public void setMergesUrl(String mergesUrl){
		this.mergesUrl = mergesUrl;
	}

	public String getMergesUrl(){
		return mergesUrl;
	}

	public void setDownloadsUrl(String downloadsUrl){
		this.downloadsUrl = downloadsUrl;
	}

	public String getDownloadsUrl(){
		return downloadsUrl;
	}

	public void setHasIssues(boolean hasIssues){
		this.hasIssues = hasIssues;
	}

	public boolean isHasIssues(){
		return hasIssues;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setContentsUrl(String contentsUrl){
		this.contentsUrl = contentsUrl;
	}

	public String getContentsUrl(){
		return contentsUrl;
	}

	public void setMirrorUrl(Object mirrorUrl){
		this.mirrorUrl = mirrorUrl;
	}

	public Object getMirrorUrl(){
		return mirrorUrl;
	}

	public void setMilestonesUrl(String milestonesUrl){
		this.milestonesUrl = milestonesUrl;
	}

	public String getMilestonesUrl(){
		return milestonesUrl;
	}

	public void setTeamsUrl(String teamsUrl){
		this.teamsUrl = teamsUrl;
	}

	public String getTeamsUrl(){
		return teamsUrl;
	}

	public void setFork(boolean fork){
		this.fork = fork;
	}

	public boolean isFork(){
		return fork;
	}

	public void setIssuesUrl(String issuesUrl){
		this.issuesUrl = issuesUrl;
	}

	public String getIssuesUrl(){
		return issuesUrl;
	}

	public void setEventsUrl(String eventsUrl){
		this.eventsUrl = eventsUrl;
	}

	public String getEventsUrl(){
		return eventsUrl;
	}

	public void setIssueEventsUrl(String issueEventsUrl){
		this.issueEventsUrl = issueEventsUrl;
	}

	public String getIssueEventsUrl(){
		return issueEventsUrl;
	}

	public void setAssigneesUrl(String assigneesUrl){
		this.assigneesUrl = assigneesUrl;
	}

	public String getAssigneesUrl(){
		return assigneesUrl;
	}

	public void setOpenIssues(int openIssues){
		this.openIssues = openIssues;
	}

	public int getOpenIssues(){
		return openIssues;
	}

	public void setWatchersCount(int watchersCount){
		this.watchersCount = watchersCount;
	}

	public int getWatchersCount(){
		return watchersCount;
	}

	public void setNodeId(String nodeId){
		this.nodeId = nodeId;
	}

	public String getNodeId(){
		return nodeId;
	}

	public void setHomepage(String homepage){
		this.homepage = homepage;
	}

	public String getHomepage(){
		return homepage;
	}

	public void setForksCount(int forksCount){
		this.forksCount = forksCount;
	}

	public int getForksCount(){
		return forksCount;
	}

	@Override
 	public String toString(){
		return 
			"Repo{" + 
			"stargazers_count = '" + stargazersCount + '\'' + 
			",pushed_at = '" + pushedAt + '\'' + 
			",subscription_url = '" + subscriptionUrl + '\'' + 
			",language = '" + language + '\'' + 
			",branches_url = '" + branchesUrl + '\'' + 
			",issue_comment_url = '" + issueCommentUrl + '\'' + 
			",labels_url = '" + labelsUrl + '\'' + 
			",subscribers_url = '" + subscribersUrl + '\'' + 
			",releases_url = '" + releasesUrl + '\'' + 
			",svn_url = '" + svnUrl + '\'' + 
			",id = '" + id + '\'' + 
			",forks = '" + forks + '\'' + 
			",archive_url = '" + archiveUrl + '\'' + 
			",git_refs_url = '" + gitRefsUrl + '\'' + 
			",forks_url = '" + forksUrl + '\'' + 
			",statuses_url = '" + statusesUrl + '\'' + 
			",ssh_url = '" + sshUrl + '\'' + 
			",license = '" + license + '\'' + 
			",full_name = '" + fullName + '\'' + 
			",size = '" + size + '\'' + 
			",languages_url = '" + languagesUrl + '\'' + 
			",html_url = '" + htmlUrl + '\'' + 
			",collaborators_url = '" + collaboratorsUrl + '\'' + 
			",clone_url = '" + cloneUrl + '\'' + 
			",name = '" + name + '\'' + 
			",pulls_url = '" + pullsUrl + '\'' + 
			",default_branch = '" + defaultBranch + '\'' + 
			",hooks_url = '" + hooksUrl + '\'' + 
			",trees_url = '" + treesUrl + '\'' + 
			",tags_url = '" + tagsUrl + '\'' + 
			",private = '" + jsonMemberPrivate + '\'' + 
			",contributors_url = '" + contributorsUrl + '\'' + 
			",has_downloads = '" + hasDownloads + '\'' + 
			",notifications_url = '" + notificationsUrl + '\'' + 
			",open_issues_count = '" + openIssuesCount + '\'' + 
			",description = '" + description + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",watchers = '" + watchers + '\'' + 
			",keys_url = '" + keysUrl + '\'' + 
			",deployments_url = '" + deploymentsUrl + '\'' + 
			",has_projects = '" + hasProjects + '\'' + 
			",archived = '" + archived + '\'' + 
			",has_wiki = '" + hasWiki + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",comments_url = '" + commentsUrl + '\'' + 
			",stargazers_url = '" + stargazersUrl + '\'' + 
			",disabled = '" + disabled + '\'' + 
			",git_url = '" + gitUrl + '\'' + 
			",has_pages = '" + hasPages + '\'' + 
			",owner = '" + owner + '\'' + 
			",commits_url = '" + commitsUrl + '\'' + 
			",compare_url = '" + compareUrl + '\'' + 
			",git_commits_url = '" + gitCommitsUrl + '\'' + 
			",blobs_url = '" + blobsUrl + '\'' + 
			",git_tags_url = '" + gitTagsUrl + '\'' + 
			",merges_url = '" + mergesUrl + '\'' + 
			",downloads_url = '" + downloadsUrl + '\'' + 
			",has_issues = '" + hasIssues + '\'' + 
			",url = '" + url + '\'' + 
			",contents_url = '" + contentsUrl + '\'' + 
			",mirror_url = '" + mirrorUrl + '\'' + 
			",milestones_url = '" + milestonesUrl + '\'' + 
			",teams_url = '" + teamsUrl + '\'' + 
			",fork = '" + fork + '\'' + 
			",issues_url = '" + issuesUrl + '\'' + 
			",events_url = '" + eventsUrl + '\'' + 
			",issue_events_url = '" + issueEventsUrl + '\'' + 
			",assignees_url = '" + assigneesUrl + '\'' + 
			",open_issues = '" + openIssues + '\'' + 
			",watchers_count = '" + watchersCount + '\'' + 
			",node_id = '" + nodeId + '\'' + 
			",homepage = '" + homepage + '\'' + 
			",forks_count = '" + forksCount + '\'' + 
			"}";
		}
}