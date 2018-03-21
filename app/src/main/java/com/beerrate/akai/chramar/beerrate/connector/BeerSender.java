package com.beerrate.akai.chramar.beerrate.connector;


import com.beerrate.akai.chramar.beerrate.datamodel.Beer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface BeerSender {
    @POST("beer")
    Call<Beer> createBeer(@Body Beer beer);

}
