package br.com.devdiegopirutti.mainactivity.models;

import com.google.gson.annotations.SerializedName;

public class Statuses {

    @SerializedName("href")
    private String href;

    public String getHref() {
        return href;
    }

    @Override
    public String toString() {
        return
                "Statuses{" +
                        "href = '" + href + '\'' +
                        "}";
    }
}