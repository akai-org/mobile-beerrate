package com.beerrate.akai.chramar.beerrate.connector;

import com.beerrate.akai.chramar.beerrate.datamodel.Beer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface BeerDownload{
    @GET("beer")
    Call<List<Beer>> listBeers();
    @GET("beer/{id}")
    Call<Beer> getBeer(@Path("id") long id);
}