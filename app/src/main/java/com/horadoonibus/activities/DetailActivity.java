package com.horadoonibus.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.horadoonibus.R;
import com.horadoonibus.api.RetrofitAPI;
import com.horadoonibus.api.RetrofitService;
import com.horadoonibus.helpers.Utils;
import com.horadoonibus.model.DataContext;
import com.horadoonibus.model.DataSingleton;
import com.horadoonibus.model.Linha;
import com.horadoonibus.model.TabelaLinha;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailActivity extends AppCompatActivity {
    private static Linha linha;
    public static final String LINHA = "LINHA";
    private Toolbar toolbar;
    private RetrofitAPI api;
    private DataContext db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle data = getIntent().getExtras();
        db = DataSingleton.getInstance(this);
        toolbar = findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbar);
        if (data != null) {
            linha = (Linha) data.get(LINHA);
            toolbar.setTitle(linha.getNome());
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Retrofit retrofit = RetrofitService.getInstance();
        api = retrofit.create(RetrofitAPI.class);

        Call<List<Object>> responseTabelaLinha = api.getTabelaLinha(linha.getCodigo(), Utils.AUTH_KEY);
        responseTabelaLinha.enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                try {
                    Gson gson = new Gson();
                    String json = gson.toJson(response.body());

                    Type collectionType = new TypeToken<List<TabelaLinha>>() {
                    }.getType();
                    List<TabelaLinha> lista = gson.fromJson(json, collectionType);
                    for (TabelaLinha tl : lista) {
                        db.insert(tl);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {

            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public void weekdays(View view) {
        Intent it = new Intent(DetailActivity.this, HorariosActivity.class);
        it.putExtra("LINHA", linha);
        it.putExtra("DIA", 1);
        startActivity(it);
    }

    public void saturdays(View view) {
        Intent it = new Intent(DetailActivity.this, HorariosActivity.class);
        it.putExtra("LINHA", linha);
        it.putExtra("DIA", 2);
        startActivity(it);
    }

    public void sundays(View view) {
        Intent it = new Intent(DetailActivity.this, HorariosActivity.class);
        it.putExtra("LINHA", linha);
        it.putExtra("DIA", 3);
        startActivity(it);
    }

    public void realTime(View view) {
        Intent it = new Intent(DetailActivity.this, HorariosActivity.class);
        it.putExtra("LINHA", linha);
        startActivity(it);
    }
}
