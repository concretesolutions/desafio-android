package br.com.devdiegopirutti.mainactivity.models;

import com.google.gson.annotations.SerializedName;

public class Self {

    @SerializedName("href")
    private String href;

    public String getHref() {
        return href;
    }

    @Override
    public String toString() {
        return
                "Self{" +
                        "href = '" + href + '\'' +
                        "}";
    }
}