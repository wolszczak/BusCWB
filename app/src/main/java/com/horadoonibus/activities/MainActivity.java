package com.horadoonibus.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.horadoonibus.R;
import com.horadoonibus.api.RetrofitAPI;
import com.horadoonibus.api.RetrofitService;
import com.horadoonibus.helpers.LinhasAdapter;
import com.horadoonibus.helpers.Utils;
import com.horadoonibus.model.DataContext;
import com.horadoonibus.model.DataSingleton;
import com.horadoonibus.model.Linha;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements LinhasAdapter.OnClickHandler {

    private RetrofitAPI api;
    private DataContext db;
    private SwipeRefreshLayout swipeLayout;
    private List<Linha> linhas = new ArrayList<>();
    private LinhasAdapter mLinhasAdapter;
    private GridLayoutManager gridLayoutManager;
    private Long limit = 20L;
    private boolean isScrolling = false;
    private int currentItems, totalItems, scrollOutItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = RetrofitService.getInstance();
        api = retrofit.create(RetrofitAPI.class);
        db = DataSingleton.getInstance(this);
        swipeLayout = findViewById(R.id.swipeLayout);
        swipeLayout.setRefreshing(true);
        mLinhasAdapter = new LinhasAdapter(MainActivity.this);
        gridLayoutManager = new GridLayoutManager(this, 1);
        RecyclerView recyclerView = findViewById(R.id.rvLinhas);
        recyclerView.setLayoutManager(gridLayoutManager);
        Call<Object> responseLinhas = api.getLinhas(Utils.AUTH_KEY);
        responseLinhas.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                try {
                    Gson gson = new Gson();
                    String json = gson.toJson(response.body());

                    Type collectionType = new TypeToken<List<Linha>>(){}.getType();
                    List<Linha> lista = gson.fromJson(json, collectionType);
                    for (Linha linha : lista) {
                        db.insert(linha);
                    }
                    linhas =  new ArrayList<>();
                    linhas.addAll(db.getAllOrdered(Linha.class,"NOME",limit,true));
                    swipeLayout.setRefreshing(false);
                    mLinhasAdapter.setLinhas(linhas);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mLinhasAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = gridLayoutManager.getChildCount();
                totalItems = gridLayoutManager.getItemCount();
                scrollOutItems = gridLayoutManager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                        isScrolling = false;
                        limit +=20;
                    try {
                        linhas = new ArrayList<>();
                        linhas.addAll(db.getAllOrdered(Linha.class,"NOME",limit,true));
                        mLinhasAdapter.setLinhas(linhas);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        swipeLayout.setOnRefreshListener(() -> {
            linhas.clear();
            mLinhasAdapter.setLinhas(null);
            try {
                linhas = new ArrayList<>();
                linhas.addAll(db.getAllOrdered(Linha.class,"NOME",limit,true));
                mLinhasAdapter.setLinhas(linhas);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void OnClickHandler(Linha linha) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("LINHA", linha);
        startActivity(intent);
    }


}
