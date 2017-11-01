package com.programandoando.vick.retrofitapirest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
//import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.programandoando.vick.retrofitapirest.postsApi.data.model.Comments;
import com.programandoando.vick.retrofitapirest.postsApi.data.model.Posts;
import com.programandoando.vick.retrofitapirest.postsApi.data.api.retrofit.RestApiAdapter;
import com.programandoando.vick.retrofitapirest.postsApi.data.api.client.Service;
import com.programandoando.vick.retrofitapirest.postsApi.data.model.SinglePost;
import com.programandoando.vick.retrofitapirest.postsApi.data.model.Users;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActitivityLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getUsers();
        //getSinglePost();
        //getPosts();
        //getComments();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getUsers() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        Service service = restApiAdapter.getClientService();

        Call<JsonArray> users = service.getUsers();

        users.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                JsonArray usersResponse = response.body().getAsJsonArray();

                Gson gson = new Gson();

                Users usersData[] = gson.fromJson(usersResponse.toString(),Users[].class);

                List<Users> userModel = Arrays.asList(usersData);

                Log.w(TAG,"Usuario 1 "+ userModel.get(0).toString());
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

            }
        });
    }

    private void getSinglePost() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        Service service = restApiAdapter.getClientService();

        Call<JsonObject> postSingle = service.getPostSingle(2);

        postSingle.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject dataSinglePost = response.body().getAsJsonObject();

                Gson gson = new Gson();
                SinglePost singlePostData = gson.fromJson(dataSinglePost.toString(),SinglePost.class);
                Log.w(TAG,"Post 2 => "+ singlePostData.toString());

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    private void getComments() {

        RestApiAdapter restApiAdapter = new RestApiAdapter();

        Service service = restApiAdapter.getClientService();

        Call<JsonArray> call = service.getComments();

        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                JsonArray comments = response.body().getAsJsonArray();

                Gson gson = new Gson();

                Comments[] comment = gson.fromJson(comments.toString(),Comments[].class);
                List<Comments> modelComments = Arrays.asList(comment);

                Log.w(TAG,"Comentario uno "+ modelComments.get(0).toString());
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

            }
        });
    }

    private void getPosts()  {

        RestApiAdapter restApiAdapter = new RestApiAdapter();

        Service service = restApiAdapter.getClientService();

        Call<JsonArray> call = service.getDataPosts();

        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> postListcall, Response<JsonArray> response) {

                JsonArray posts = response.body().getAsJsonArray();
                Gson gson = new Gson();
                Posts[] post = gson.fromJson(posts.toString(), Posts[].class);
                List<Posts> modelPost =  Arrays.asList(post);

                Log.w(TAG, "onResponse: " + modelPost.get(0).toString() );
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.w(TAG,"Posts falsos "+ t.toString() );
            }
        });
    }
}
