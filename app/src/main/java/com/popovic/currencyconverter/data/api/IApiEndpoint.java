package com.popovic.currencyconverter.data.api;


import com.popovic.currencyconverter.data.models.CurrencyModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IApiEndpoint {

    @GET("rates/daily")
    Call<List<CurrencyModel>> getCurrencyList();
}
