package com.beerrate.akai.chramar.beerrate.connector;

@FunctionalInterface
public interface SuccessCallback<T> {

    void onSuccess(T t);

}
