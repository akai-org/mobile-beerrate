package com.beerrate.akai.chramar.beerrate.connector;

import com.beerrate.akai.chramar.beerrate.datamodel.Beer;

import java.util.List;

/**
 * Created by Programista on 21.03.2018.
 */

public interface BeerDataProvider {

    public Beer getBeerById(long id);

    public List<Beer> getAll();
}
