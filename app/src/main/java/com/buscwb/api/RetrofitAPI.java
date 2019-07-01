package com.buscwb.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Wolszczak on 27/06/2019.
 */
public interface RetrofitAPI {

    @GET("/getLinhas.php")
    Call<Object> getLinhas(@Query(value = "c", encoded = true) String userkey);

    @GET("/getTabelaLinha.php")
    Call<List<Object>> getTabelaLinha(@Query(value = "linha", encoded = true) String linha,
                                           @Query(value = "c", encoded = true) String userkey);

    @GET("/getVeiculosLinha.php")
    Call<List<Object>> getVeiculos(@Query(value = "linha", encoded = true) String linha,
                                   @Query(value = "c", encoded = true) String userkey);

    @GET("/getShapeLinha.php")
    Call<List<Object>> getShapeLinha(@Query(value = "linha", encoded = true) String linha,
                                   @Query(value = "c", encoded = true) String userkey);

}
