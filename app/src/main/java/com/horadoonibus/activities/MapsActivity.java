package com.horadoonibus.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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
import com.horadoonibus.model.TabelaLinha;
import com.horadoonibus.model.Veiculos;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static Linha linha;
    public Veiculos veiculo;
    private Location myLocation;
    public List<ShapeLinha> shapes;
    private List<Veiculos> veiculos;
    public static final String LINHA = "LINHA";
    private Toolbar toolbar;
    private RetrofitAPI api;
    private DataContext db;
    private LocationManager locationManager;

    public Location getMyLocation() {
        return myLocation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        veiculos = new ArrayList<>();
        shapes = new ArrayList<>();
        Bundle data = getIntent().getExtras();
        db = DataSingleton.getInstance(this);
        toolbar = findViewById(R.id.toolbar);
        if (data != null) {
            veiculo = (Veiculos) data.get("VEICULOS");
            toolbar.setTitle(veiculo.getCodigo());
        }
        Retrofit retrofit = RetrofitService.getInstance();
        api = retrofit.create(RetrofitAPI.class);

        Call<List<Object>> responseVeiculos = api.getVeiculos(linha.getCodigo(), Utils.AUTH_KEY);
        responseVeiculos.enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                Gson gson = new Gson();
                String json = gson.toJson(response.body());

                Type collectionType = new TypeToken<List<Veiculos>>() {
                }.getType();
                veiculos = gson.fromJson(json, collectionType);
            }

            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {

            }
        });

        Call<List<Object>> responseShape = api.getShapeLinha(linha.getCodigo(), Utils.AUTH_KEY);
        responseShape.enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                Gson gson = new Gson();
                String json = gson.toJson(response.body());
                Type collectionType = new TypeToken<List<ShapeLinha>>() {
                }.getType();
                shapes = gson.fromJson(json, collectionType);
            }
            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {

            }
        });

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        myLocation = getLastKnownLocation();//locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        // Add a marker in Sydney and move the camera
        LatLng local = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(local));

        for(ShapeLinha sl : shapes){
            LatLng coordenadas = new LatLng(sl.getLatitude(),sl.getLongitude());
            Marker m = mMap.addMarker(new MarkerOptions()
                    .position(coordenadas)
                    .flat(true)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.background_orange))
                    .rotation(myLocation.getBearing()));
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private Location getLastKnownLocation() {
        locationManager = (LocationManager) this.getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
            Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }
}
