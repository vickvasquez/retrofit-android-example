package com.programandoando.vick.retrofitapirest.postsApi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Juan on 31/10/2017.
 */

public interface Service {
    @GET(Constants.POSTS)
    Call<JsonArray> getDataPosts();

    @GET(Constants.COMMENTS)
    Call<JsonObject> getComments();
}
