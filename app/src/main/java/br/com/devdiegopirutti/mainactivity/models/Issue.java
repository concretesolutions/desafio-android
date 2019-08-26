package br.com.devdiegopirutti.mainactivity.models;

import com.google.gson.annotations.SerializedName;

public class Issue {

    @SerializedName("href")
    private String href;

    public String getHref() {
        return href;
    }

    @Override
    public String toString() {
        return
                "Issue{" +
                        "href = '" + href + '\'' +
                        "}";
    }
}