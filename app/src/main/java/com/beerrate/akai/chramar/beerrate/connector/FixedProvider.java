package com.beerrate.akai.chramar.beerrate.connector;


import com.beerrate.akai.chramar.beerrate.datamodel.Beer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class FixedProvider implements BeerDataProvider{

    private ArrayList<Beer> beerList = new ArrayList<>();
    private Random r = new Random();

    FixedProvider(){
        beerList.add(new Beer(1, "Tyskie", "Lech", "Jasne", "Polska", 10, 5.5f, 2.6f));
        beerList.add(new Beer(2, "Żubr", "Lech", "Jasne", "Polska", 10, 5.5f, 2.6f));
        beerList.add(new Beer(3, "Tatra", "Lech", "Jasne", "Polska", 10, 5.5f, 2.6f));
        beerList.add(new Beer(4, "Preła eksport", "Lech", "Jasne", "Polska", 10, 5.5f, 2.6f));
        beerList.add(new Beer(5, "Lech", "Lech", "Jasne", "Polska", 10, 5.5f, 2.6f));
        beerList.add(new Beer(6, "Lech Pils", "Lech", "Jasne", "Polska", 10, 5.5f, 2.6f));
        beerList.add(new Beer(7, "Lech Premium", "Lech", "Jasne", "Polska", 10, 5.5f, 2.6f));
        beerList.add(new Beer(8, "Żywiec", "Lech", "Jasne", "Polska", 10, 5.5f, 2.6f));
        beerList.add(new Beer(9, "Piast", "Lech", "Jasne", "Polska", 10, 5.5f, 2.6f));
        beerList.add(new Beer(10, "Bosman", "Lech", "Jasne", "Polska", 10, 5.5f, 2.6f));
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
