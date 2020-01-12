package com.example.github_api_concrete.model.pojo.pullrequests;

import com.google.gson.annotations.SerializedName;

public class Statuses{

	@SerializedName("href")
	private String href;

	public void setHref(String href){
		this.href = href;
	}

	public String getHref(){
		return href;
	}

	@Override
 	public String toString(){
		return 
			"Statuses{" + 
			"href = '" + href + '\'' + 
			"}";
		}
}