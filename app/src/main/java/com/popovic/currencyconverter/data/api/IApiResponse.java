package com.popovic.currencyconverter.data.api;

public interface IApiResponse {
    void onSuccess();

    void onError(String s);
}
