package com.beerrate.akai.chramar.beerrate.connector;


import com.beerrate.akai.chramar.beerrate.datamodel.Beer;

import java.util.logging.Level;
import java.util.logging.Logger;

class EmptySender implements BeerDataSender {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void addBeer(Beer beer) {
        logger.log(Level.INFO,"added beer " + beer.getName());
    }
}
