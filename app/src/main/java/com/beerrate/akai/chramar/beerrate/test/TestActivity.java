package com.beerrate.akai.chramar.beerrate.test;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.beerrate.akai.chramar.beerrate.R;
import com.beerrate.akai.chramar.beerrate.connector.ConnectorFacade;
import com.beerrate.akai.chramar.beerrate.connector.SuccessCallback;
import com.beerrate.akai.chramar.beerrate.datamodel.Beer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.textView2)
    TextView textView;

    private ConnectorFacade connectorFacade = new ConnectorFacade();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        FloatingActionButton fab = findViewById(R.id.fab);

        Beer newBeer = new Beer(1,"a","b","c","c",123,2.1f,4);

        SuccessCallback<List<Beer>> listCallback = list -> textView.setText(list.stream().map(Beer::getName).reduce(" ", String::concat));

        SuccessCallback<Beer> beerCallback = beer -> textView.setText(beer.getName());

        SuccessCallback<Beer> newBeerSuccessCallback = beer -> Toast.makeText(getApplicationContext(),"Send",Toast.LENGTH_LONG).show();

        fab.setOnClickListener(view -> connectorFacade.sendBeer(newBeer,newBeerSuccessCallback));

    }

}