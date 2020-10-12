package com.beerrate.akai.chramar.beerrate.datamodel;

public class Review {
    private String notes;
    private float rate;

    public Review(String notes, float rate) {
        this.notes = notes;
        this.rate = rate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
