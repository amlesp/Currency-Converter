package com.popovic.currencyconverter.data.api;

import android.content.Context;
import android.util.Log;

import com.popovic.currencyconverter.data.models.CurrencyModel;
import com.popovic.currencyconverter.data.persist.PersistCurrency;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCurrencyListTask {

    private final String TAG = GetCurrencyListTask.class.getSimpleName();
    private Context context;
    private IApiResponse mListener;

    public GetCurrencyListTask(Context context, IApiResponse iApiResponse) {
        this.context = context;
        this.mListener = iApiResponse;
        getCurrency();
    }

    private void getCurrency() {
        IApiEndpoint service = RetrofitClient.getClient(context).create(IApiEndpoint.class);
        Call<List<CurrencyModel>> call = service.getCurrencyList();
        call.enqueue(new Callback<List<CurrencyModel>>() {
            @Override
            public void onResponse(Call<List<CurrencyModel>> call, Response<List<CurrencyModel>> response) {
                if (response.body() != null && response.body().size() > 0) {
                    PersistCurrency.persist(context, response.body());
                    mListener.onSuccess();
                    Log.d(TAG, response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<CurrencyModel>> call, Throwable t) {
                mListener.onError(t.toString());
                Log.d(TAG, t.toString());
            }
        });
    }
}
