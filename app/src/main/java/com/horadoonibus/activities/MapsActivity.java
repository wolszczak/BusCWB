package com.horadoonibus.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
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

import static android.content.Context.LOCATION_SERVICE;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private static Linha linha;
    public Veiculos veiculo;
    private Location myLocation,myLastLocation;
    public List<ShapeLinha> shapes;
    private List<Veiculos> veiculos;
    public static final String LINHA = "LINHA";
    private RetrofitAPI api;
    private DataContext db;
    private LocationManager locationManager;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        buildGoogleApiClient();
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        veiculos = new ArrayList<>();
        shapes = new ArrayList<>();
        Bundle data = getIntent().getExtras();
        db = DataSingleton.getInstance(this);
//        toolbar = findViewById(R.id.toolbar);
        if (data != null) {
            linha = (Linha) data.get("LINHA");
//            toolbar.setTitle(veiculo.getCodigo());
        }
        Retrofit retrofit = RetrofitService.getInstance();
        api = retrofit.create(RetrofitAPI.class);





    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
//        LatLng local = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(local));

//        for (ShapeLinha sl : shapes) {
//            LatLng coordenadas = new LatLng(sl.getLatitude(), sl.getLongitude());
//            Marker m = mMap.addMarker(new MarkerOptions()
//                    .position(coordenadas)
//                    .flat(true)
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.background_orange))
//                    .rotation(myLocation.getBearing()));
//        }
    }

//    protected synchronized void buildGoogleApiClient() {
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
//                    @RequiresApi(api = Build.VERSION_CODES.M)
//                    @Override
//                    public void onConnected(@Nullable Bundle bundle) {
//                        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                            return;
//                        }
//                        myLocation = LocationServices.FusedLocationApi.getLastLocation(
//                                mGoogleApiClient);
//                    }
//
//                    @Override
//                    public void onConnectionSuspended(int i) {
//
//                    }
//                })
//                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
//                    @Override
//                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//                    }
//                })
//                .addApi(LocationServices.API)
//                .build();
//    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
