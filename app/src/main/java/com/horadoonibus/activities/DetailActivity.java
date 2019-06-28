package com.horadoonibus.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.horadoonibus.R;
import com.horadoonibus.model.Linha;

public class DetailActivity extends AppCompatActivity {
    private static Linha linha;
    public static final String LINHA = "LINHA";
    private Toolbar toolbar;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle data = getIntent().getExtras();

        toolbar =  findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbar);
        if (data != null) {
            linha = (Linha) data.get(LINHA);
            toolbar.setTitle(linha.getNome());
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
