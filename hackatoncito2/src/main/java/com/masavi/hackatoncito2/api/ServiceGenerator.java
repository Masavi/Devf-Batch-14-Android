package com.masavi.hackatoncito2.api;

import com.masavi.hackatoncito2.util.NetworkConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Masavi on 11/5/2017.
 */

public class ServiceGenerator {

    private static Retrofit retrofit;

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(NetworkConstants.BASE_URL);

    public static BooksAPI createService(){
        if (retrofit == null) {
            retrofit = builder.build();
        }
        return  retrofit.create(BooksAPI.class);
    }
}
