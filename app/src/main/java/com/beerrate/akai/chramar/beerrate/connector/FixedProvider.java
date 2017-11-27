package com.beerrate.akai.chramar.beerrate.connector;


import com.beerrate.akai.chramar.beerrate.datamodel.Beer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class FixedProvider implements BeerDataProvider{

    private ArrayList<Beer> beerList = new ArrayList<>();
    private Random r = new Random();

    FixedProvider(){
        beerList.add(new Beer(1,"name","inny","APA","Nie pamietam",12,12,4));
        beerList.add(new Beer(1,"asd","agbs bx","APA","Nie pamietam",12,12,4));
        beerList.add(new Beer(1,"hgfd","agrsv","APA","Nie pamietam",12,12,4));
        beerList.add(new Beer(1,"stjntsr","arbs","APA","Nie pamietam",12,12,4));
        beerList.add(new Beer(1,"sagrhtdrnn","sdfgsf","APA","Nie pamietam",12,12,4));
    }

    @Override
    public Beer getBeerById(long id) {
        return beerList.get(r.nextInt(beerList.size()));
    }

    @Override
    public List<Beer> getAll() {
        return beerList;
    }
}
