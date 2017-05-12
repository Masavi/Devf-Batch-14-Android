package com.masavi.hackatoncito2.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Masavi on 11/5/2017.
 */

public class Author {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                '}';
    }
}
