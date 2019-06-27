package com.horadoonibus.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Wolszczak on 27/06/2019.
 */
public interface IRetrofit {

    @GET("/getLinhas.php")
    Call<Object> getLinhas(@Query(value = "linha",encoded = true) String linha,
                                @Query(value = "c",encoded = true) String userkey);

    @GET("/getTabelaLinha.php")
    Call<Object> getTabelaLinha(@Query(value = "linha",encoded = true) String linha,
                                @Query(value = "c",encoded = true) String userkey);

    @GET("/getVeiculos.php")
    Call<Object> getVeiculos(@Query(value = "linha",encoded = true) String linha,
                                @Query(value = "c",encoded = true) String userkey);

}
