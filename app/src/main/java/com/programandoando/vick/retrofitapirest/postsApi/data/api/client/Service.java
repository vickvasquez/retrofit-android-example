package com.programandoando.vick.retrofitapirest.postsApi.data.api.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.programandoando.vick.retrofitapirest.postsApi.data.api.Constants;
import com.programandoando.vick.retrofitapirest.postsApi.data.model.Comments;
import com.programandoando.vick.retrofitapirest.postsApi.data.model.CommentsPosts;
import com.programandoando.vick.retrofitapirest.postsApi.data.model.Posts;
import com.programandoando.vick.retrofitapirest.postsApi.data.model.SinglePost;
import com.programandoando.vick.retrofitapirest.postsApi.data.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Juan on 31/10/2017.
 */

public interface Service {
    @GET(Constants.POSTS)
    Call<List<Posts>> getDataPosts();

    @GET(Constants.COMMENTS)
    Call<List<Comments>> getComments();

    @GET(Constants.USERS)
    Call<List<Users>> getUsers();

    @GET(Constants.POSTS+"/{idPost}")
    Call<SinglePost> getPostSingle(@Path("idPost") Integer idPost);

    @GET(Constants.COMMENTS+"/")
    Call<List<CommentsPosts>> getPosts(@Query("postId") Integer postId);
}
