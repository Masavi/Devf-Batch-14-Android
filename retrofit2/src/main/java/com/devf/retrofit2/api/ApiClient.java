package com.devf.retrofit2.api;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Ken on 09/05/17.
 */

public interface ApiClient {

    @GET("/v2.9/me?fields=id,name,education,email,birthday,gender,hometown,about,picture&access_token=EAACEdEose0cBAAMEpVzYSDhP8baZA2x0K5pZARFO9r2x2JRuqWwY0f5nx114qVLUe5rarmbqLJNNlmZCtClT6NRiXUmYIZBr8REY0YZAK4BiiB1ZC9sq4ABS9ZAVshAb0OEHbrRgOJZBwFAZAsi6DO9lqSMfmdiVjwIVa7KvS6l1MA74EmFLoUP3jmjiWJzfnz8oZD")
    Call<ResponseBody> getPublicProfile();

}
