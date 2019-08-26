package br.com.devdiegopirutti.mainactivity.models;

import com.google.gson.annotations.SerializedName;

public class ReviewComments {

    @SerializedName("href")
    private String href;

    public String getHref() {
        return href;
    }

    @Override
    public String toString() {
        return
                "ReviewComments{" +
                        "href = '" + href + '\'' +
                        "}";
    }
}