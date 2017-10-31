package com.programandoando.vick.retrofitapirest.postsApi;

import java.util.logging.Level;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Juan on 31/10/2017.
 */

public class RestApiAdapter {

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)).build();

    public  Service getClientService() {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Constants.ROOT_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit.create(Service.class);
    }
}
