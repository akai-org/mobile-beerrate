package com.beerrate.akai.chramar.beerrate.connector;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class HttpRequestCallback<T> implements Callback<T> {

    private SuccessCallback<T> successCallback;

    HttpRequestCallback(SuccessCallback<T> successCallback) {
        this.successCallback = successCallback;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            T body = response.body();
            successCallback.onSuccess(body);
        } else{
            Log.e("HTTP_RESPONSE_ERROR", response.code()+" "+response.message());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.e("HTTP_RESPONSE_ERROR", t.getMessage());
    }
}