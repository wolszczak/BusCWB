package com.horadoonibus.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wolszczak on 27/06/2019.
 */
public class RetrofitService {
    private static Retrofit retrofit = null;

    public static Retrofit getInstance() {
        if (retrofit==null) {
            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://transporteservico.urbs.curitiba.pr.gov.br")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
