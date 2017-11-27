package com.beerrate.akai.chramar.beerrate.connector;

import com.beerrate.akai.chramar.beerrate.datamodel.Beer;

import java.util.List;

interface BeerDataProvider {

    Beer getBeerById(long id);
    List<Beer> getAll();

}
