package com.sensedia.corebanking.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by renan on 19/03/16.
 */
public class ServiceGenerator {

    public static final String URLBASEAPI = "http://api.reimaginebanking.com";

    private ServiceGenerator() { }

    public static <S> S createService(Class<S> serviceClass) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
                .create();

        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(URLBASEAPI)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return adapter.create(serviceClass);
    }
}
