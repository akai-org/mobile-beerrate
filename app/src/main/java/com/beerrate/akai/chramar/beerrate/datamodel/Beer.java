package com.beerrate.akai.chramar.beerrate.datamodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Beer {

    private long id;
    private String name;
    private String brewery;
    private String style;
    private String whereBought;
    private int ibu;
    private float alc;
    private float price;

    public Beer(long id, String name, String brewery, String style, String whereBought, int ibu, float alc, float price) {
        this.id = id;
        this.name = name;
        this.brewery = brewery;
        this.style = style;
        this.whereBought = whereBought;
        this.ibu = ibu;
        this.alc = alc;
        this.price = price;
    }



}
