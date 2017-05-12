package com.masavi.hackatoncito2.api;

import com.masavi.hackatoncito2.models.BookObject;
import com.masavi.hackatoncito2.util.NetworkConstants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Masavi on 11/5/2017.
 */

public interface BooksAPI {

    @GET(NetworkConstants.PATH_SEARCH)
    public Call<BookObject> searchBooksByTitle(
        @Query(NetworkConstants.PARAM_TITLE) String title);

    /*@GET("http://tesseractspace.com/biblioteca/api/?title=elements")
    public Call<BookObject> searchBooksByTitle();*/

    @GET(NetworkConstants.PATH_SEARCH)
    public Call<BookObject> searchBooksByAuthor(
            @Query(NetworkConstants.PARAM_AUTHOR) String author);
}

