package com.masavi.hackatoncito2.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Masavi on 11/5/2017.
 */

public class BookObject {

    @SerializedName("books")
    private List<Book> books = new ArrayList<>();

    @SerializedName("results")
    private int results;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "BookObject{" +
                "books=" + books +
                ", results='" + results + '\'' +
                '}';
    }
}
