package com.devf.spotifyplayer.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Masavi on 11/5/2017.
 */

public class Image {

    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Image{" +
                "url='" + url + '\'' +
                '}';
    }
}
