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
import com.programandoando.vick.retrofitapirest.postsApi.Posts;
import com.programandoando.vick.retrofitapirest.postsApi.RestApiAdapter;
import com.programandoando.vick.retrofitapirest.postsApi.Service;

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

        getPosts();
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

    private void getPosts() {

        RestApiAdapter restApiAdapter = new RestApiAdapter();

        Service service = restApiAdapter.getClientService();

        Call<JsonArray> call = service.getDataPosts();

        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> postListcall, Response<JsonArray> response) {

                JsonArray posts = response.body().getAsJsonArray();
                Gson gson = new Gson();
                Posts[] post = gson.fromJson(posts.toString(), Posts[].class);
                Arrays.asList(post);

                //Log.d(TAG, "onResponse: " + postList.get(0).toString() );
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.w(TAG,"Posts falsos "+ t.toString() );
            }
        });
    }
}
