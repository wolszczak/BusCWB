package com.horadoonibus.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.horadoonibus.R;
import com.horadoonibus.api.RetrofitAPI;
import com.horadoonibus.api.RetrofitService;
import com.horadoonibus.helpers.Utils;
import com.horadoonibus.model.DataContext;
import com.horadoonibus.model.DataSingleton;
import com.horadoonibus.model.Linha;
import com.horadoonibus.model.ShapeLinha;
import com.horadoonibus.model.Veiculos;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private static Linha linha;
    public Veiculos veiculo;
    private Location myLocation, myLastLocation;
    public List<ShapeLinha> shapes;
    private List<Veiculos> veiculos;
    public static final String LINHA = "LINHA";
    private RetrofitAPI api;
    private DataContext db;
    private PolylineOptions options;
    private TextView tvLinha;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        veiculos = new ArrayList<>();
        shapes = new ArrayList<>();
        Bundle data = getIntent().getExtras();
        db = DataSingleton.getInstance(this);
        toolbar = findViewById(R.id.toolbarMap);
        tvLinha = findViewById(R.id.tvLinha);
        setSupportActionBar(toolbar);
        if (data != null) {
            linha = (Linha) data.get("LINHA");
//            tvLinha.setText(linha.getCodigo() + " - " + linha.getNome());
        }
        Retrofit retrofit = RetrofitService.getInstance();
        api = retrofit.create(RetrofitAPI.class);
        options = new PolylineOptions();
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        updateMap();

    }

    private void updateMap() {
        mMap.clear();
        Call<List<Object>> response = api.getShapeLinha(linha.getCodigo(), Utils.AUTH_KEY);
        response.enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                Gson gson = new Gson();
                String json = gson.toJson(response.body());

                Type collectionType = new TypeToken<List<ShapeLinha>>() {
                }.getType();
                json = json.replaceAll("-25,", "-25.");
                json = json.replaceAll("-49,", "-49.");
                shapes = gson.fromJson(json, collectionType);
                options = new PolylineOptions().width(10).color(Color.RED).geodesic(true).clickable(false);
                for (ShapeLinha sl : shapes) {
                    LatLng coordenadas = new LatLng(sl.getLatitude(), sl.getLongitude());
                    options.add(coordenadas);
                }
                mMap.addPolyline(options);
                mMap.moveCamera(CameraUpdateFactory
                        .newLatLngBounds(LatLngBounds.builder()
                                .include(new LatLng(shapes.get(shapes.size() / 2).getLatitude(), shapes.get(shapes.size() / 2).getLongitude()))
                                .include(new LatLng(shapes.get(0).getLatitude(), shapes.get(0).getLongitude()))
                                .build(), 50));
            }

            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {

            }

        });
        Call<List<Object>> responseVeiculos = api.getVeiculos(linha.getCodigo(), Utils.AUTH_KEY);
        responseVeiculos.enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                Gson gson = new Gson();
                String json = gson.toJson(response.body());

                Type collectionType = new TypeToken<List<Veiculos>>() {
                }.getType();
                json = json.replaceAll("-25,", "-25.");
                json = json.replaceAll("-49,", "-49.");
                veiculos = gson.fromJson(json, collectionType);

                for (Veiculos v : veiculos) {
                    mMap.addMarker(new MarkerOptions().position(new LatLng(v.getLatitude(), v.getLongitude()))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_map_35px)));
                }
            }

            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {

            }

        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
