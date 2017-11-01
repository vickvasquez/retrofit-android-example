package com.programandoando.vick.retrofitapirest.postsApi.data.api.retrofit;

import com.programandoando.vick.retrofitapirest.postsApi.data.api.Constants;
import com.programandoando.vick.retrofitapirest.postsApi.data.api.client.Service;

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

    public Service getClientService() {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Constants.ROOT_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit.create(Service.class);
    }
}
