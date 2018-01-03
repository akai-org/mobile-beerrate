package com.beerrate.akai.chramar.beerrate.connector;

import com.beerrate.akai.chramar.beerrate.datamodel.Beer;

import java.util.Collections;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectorFacade {

    private final BeerDownload beerDownload;
    private final BeerSender beerSender;

    public ConnectorFacade() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        beerDownload = retrofit.create(BeerDownload.class);
        beerSender = retrofit.create(BeerSender.class);
    }

    public void sendBeer(Beer beer,SuccessCallback<Beer> callback){
        beerSender.createBeer(beer).enqueue(new HttpRequestCallback<>(callback));
    }

    public void getAll(SuccessCallback<List<Beer>> callback) {
        beerDownload.listBeers().enqueue(new HttpRequestCallback<>(callback));
    }

    public void getAll(Callback<List<Beer>> callback) {
        beerDownload.listBeers().enqueue(callback);
    }

    public void getBeerById(long id, SuccessCallback<Beer> callback) {
        beerDownload.getBeer(id).enqueue(new HttpRequestCallback<>(callback));
    }

    public void getBeerById(long id, Callback<Beer> callback) {
        beerDownload.getBeer(id).enqueue(callback);
    }

    public List<Beer> getAll() {
        return Collections.emptyList();
    }

}
