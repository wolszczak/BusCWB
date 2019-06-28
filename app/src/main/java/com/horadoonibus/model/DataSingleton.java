package com.horadoonibus.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wolszczak on 27/06/2019.
 */
public class DataSingleton {
    private static DataContext data = null;

    public static DataContext getInstance(Context context) {
        if (data==null) {

            data = new DataContext(context);
        }
        return data;
    }
}
