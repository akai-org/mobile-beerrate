package com.beerrate.akai.chramar.beerrate.datamodel;


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

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrewery() {
        return brewery;
    }

    public String getStyle() {
        return style;
    }

    public String getWhereBought() {
        return whereBought;
    }

    public int getIbu() {
        return ibu;
    }

    public float getAlc() {
        return alc;
    }

    public float getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setWhereBought(String whereBought) {
        this.whereBought = whereBought;
    }

    public void setIbu(int ibu) {
        this.ibu = ibu;
    }

    public void setAlc(float alc) {
        this.alc = alc;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
