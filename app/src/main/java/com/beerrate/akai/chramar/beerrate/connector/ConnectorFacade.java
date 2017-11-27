package com.beerrate.akai.chramar.beerrate.connector;


import com.beerrate.akai.chramar.beerrate.datamodel.Beer;

import java.util.List;

public class ConnectorFacade {

    private BeerDataProvider beerDataProvider;
    private BeerDataSender beerDataSender;

    public ConnectorFacade() {
        this.beerDataProvider = new FixedProvider();
        this.beerDataSender = new EmptySender();
    }

    public void addBeer(Beer beer){
        beerDataSender.addBeer(beer);
    }

    public List<Beer> getAll(){
        return beerDataProvider.getAll();
    }

    public Beer getBeerById(long id){
        return beerDataProvider.getBeerById(id);
    }


}
