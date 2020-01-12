package com.example.github_api_concrete.model.pojo.pullrequests;

import com.google.gson.annotations.SerializedName;

public class Base{

	@SerializedName("ref")
	private String ref;

	@SerializedName("repo")
	private Repo repo;

	@SerializedName("label")
	private String label;

	@SerializedName("sha")
	private String sha;

	@SerializedName("user")
	private User user;

	public void setRef(String ref){
		this.ref = ref;
	}

	public String getRef(){
		return ref;
	}

	public void setRepo(Repo repo){
		this.repo = repo;
	}

	public Repo getRepo(){
		return repo;
	}

	public void setLabel(String label){
		this.label = label;
	}

	public String getLabel(){
		return label;
	}

	public void setSha(String sha){
		this.sha = sha;
	}

	public String getSha(){
		return sha;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"Base{" + 
			"ref = '" + ref + '\'' + 
			",repo = '" + repo + '\'' + 
			",label = '" + label + '\'' + 
			",sha = '" + sha + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}
}