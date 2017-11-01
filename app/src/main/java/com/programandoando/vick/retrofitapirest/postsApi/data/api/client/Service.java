package com.programandoando.vick.retrofitapirest.postsApi.data.api.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.programandoando.vick.retrofitapirest.postsApi.data.api.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Juan on 31/10/2017.
 */

public interface Service {
    @GET(Constants.POSTS)
    Call<JsonArray> getDataPosts();

    @GET(Constants.COMMENTS)
    Call<JsonArray> getComments();

    @GET(Constants.USERS)
    Call<JsonArray> getUsers();

    @GET(Constants.POSTS+"/{idPost}")
    Call<JsonObject> getPostSingle(@Path("idPost") Integer idPost);

    @GET(Constants.COMMENTS+"/?postId={idPost}")
    Call<JsonArray> getPosts(@Query("idPost") Integer idPost);
}
