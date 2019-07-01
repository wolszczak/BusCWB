package com.buscwb.activities;

import android.Manifest;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.AbsListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.buscwb.R;
import com.buscwb.api.RetrofitAPI;
import com.buscwb.api.RetrofitService;
import com.buscwb.helpers.LinhasAdapter;
import com.buscwb.helpers.Utils;
import com.buscwb.model.DataContext;
import com.buscwb.model.DataSingleton;
import com.buscwb.model.Linha;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        requestPermissions();
        Retrofit retrofit = RetrofitService.getInstance();
        api = retrofit.create(RetrofitAPI.class);
        db = DataSingleton.getInstance(this);
        swipeLayout = findViewById(R.id.swipeLayout);
        swipeLayout.setRefreshing(true);
        mLinhasAdapter = new LinhasAdapter(MainActivity.this);
        gridLayoutManager = new GridLayoutManager(this, 1);
        RecyclerView recyclerView = findViewById(R.id.rvLinhas);
        recyclerView.setLayoutManager(gridLayoutManager);
        try {
            linhas = db.getAll(Linha.class);
            int total = linhas.size();
            if (total == 0) {
                Call<Object> responseLinhas = api.getLinhas(Utils.AUTH_KEY);
                responseLinhas.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        try {
                            Gson gson = new Gson();
                            String json = gson.toJson(response.body());

                            Type collectionType = new TypeToken<List<Linha>>() {
                            }.getType();
                            List<Linha> lista = gson.fromJson(json, collectionType);
                            for (Linha linha : lista) {
                                db.insert(linha);
                            }
                            linhas = new ArrayList<>();
                            linhas.addAll(db.getAllOrdered(Linha.class, "NOME", limit, true));
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
            } else {
                mLinhasAdapter.setLinhas(linhas);
                swipeLayout.setRefreshing(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
                    limit += 20;
                    try {
                        linhas = new ArrayList<>();
                        linhas.addAll(db.getAllOrdered(Linha.class, "NOME", limit, true));
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
                linhas.addAll(db.getAllOrdered(Linha.class, "NOME", limit, true));
                mLinhasAdapter.setLinhas(linhas);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }

    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        }
    }

    @Override
    public void OnClickHandler(Linha linha) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("LINHA", linha);
        startActivity(intent);
    }


}
