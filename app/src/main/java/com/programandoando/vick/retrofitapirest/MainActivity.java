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
import com.programandoando.vick.retrofitapirest.postsApi.data.model.CommentsPosts;
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

        //getUsers();
        //getSinglePost();
        getCommentsPost();
        //getComments();
        //getPosts();
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

    private void getCommentsPost() {

        RestApiAdapter restApiAdapter = new RestApiAdapter();

        restApiAdapter
                .getClientService()
                .getPosts(1)
                .enqueue(new Callback<List<CommentsPosts>>() {
                    @Override
                    public void onResponse(Call<List<CommentsPosts>> call, Response<List<CommentsPosts>> response) {
                        Log.i(TAG,"Nu. de comentarios recuperados del post 1 => "+ response.body().size());
                    }

                    @Override
                    public void onFailure(Call<List<CommentsPosts>> call, Throwable t) {

                    }
                });

    }

    private void getUsers() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        restApiAdapter
                .getClientService()
                .getUsers()
                .enqueue(new Callback<List<Users>>() {
                    @Override
                    public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
        /*
                        JsonArray usersResponse = response.body().getAsJsonArray();

                        Gson gson = new Gson();

                        Users usersData[] = gson.fromJson(usersResponse.toString(),Users[].class);

                        List<Users> userModel = Arrays.asList(usersData);
        */

                        Log.w(TAG,"Usuario 1 "+ response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Users>> call, Throwable t) {

                    }
                });
    }

    private void getSinglePost() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        restApiAdapter
                .getClientService()
                .getPostSingle(2)
                .enqueue(new Callback<SinglePost>() {
                    @Override
                    public void onResponse(Call<SinglePost> call, Response<SinglePost> response) {
                        /*
                        JsonObject dataSinglePost = response.body().getAsJsonObject();

                        Gson gson = new Gson();
                        SinglePost singlePostData = gson.fromJson(dataSinglePost.toString(),SinglePost.class);*/

                        String singlePost = response.body().toString();

                        Log.w(TAG,"Post 2 => "+ singlePost);

                    }

                    @Override
                    public void onFailure(Call<SinglePost> call, Throwable t) {

                    }
                });
    }

    private void getComments() {

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        restApiAdapter
                    .getClientService()
                    .getComments()
                    .enqueue(new Callback<List<Comments>>() {
                        @Override
                        public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                            String comment = response.body().get(0).toString();
                            Log.w(TAG,"Comentario uno "+ comment);
                            /*
                            Gson gson = new Gson();

                            Comments[] comment = gson.fromJson(comments.toString(),Comments[].class);
                            List<Comments> modelComments = Arrays.asList(comment);

                            Log.w(TAG,"Comentario uno "+ modelComments.get(0).toString());
                            */
                        }

                        @Override
                        public void onFailure(Call<List<Comments>> call, Throwable t) {

                        }
                    });
    }

    private void getPosts()  {

        RestApiAdapter restApiAdapter = new RestApiAdapter();
  /*
        Service service = restApiAdapter.getClientService();

        Call<List<Posts>> call = service.getDataPosts();
*/
        restApiAdapter
                .getClientService()
                .getDataPosts()
                .enqueue(new Callback<List<Posts>>() {
                    @Override
                    public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                        Log.w(TAG," Respuestas => "+ response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Posts>> call, Throwable t) {
                        Log.e(TAG,"Error => "+ t.toString());
                    }
                });
    }
}
