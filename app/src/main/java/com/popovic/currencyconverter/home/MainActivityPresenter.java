package com.popovic.currencyconverter.home;

import android.content.Context;

import com.popovic.currencyconverter.R;
import com.popovic.currencyconverter.data.api.GetCurrencyListTask;
import com.popovic.currencyconverter.data.api.IApiResponse;
import com.popovic.currencyconverter.utils.ConverValue;
import com.popovic.currencyconverter.utils.NetworkUtils;

public class MainActivityPresenter implements IMainMVP.Presenter, IApiResponse {

    private final String TAG = MainActivityPresenter.class.getSimpleName();
    private IMainMVP.View mView;
    private Context context;

    public MainActivityPresenter(Context context) {
        this.context = context;
        this.mView = (IMainMVP.View) context;
    }

    @Override
    public void fetchData() {
        if (!NetworkUtils.checkConnectivity(context)) {
            mView.showError(context.getString(R.string.no_internet));
            return;
        }

        new GetCurrencyListTask((Context) mView, MainActivityPresenter.this);
    }

    @Override
    public void convert(String from, String to, Double amount) {
        mView.showResult(String.valueOf(ConverValue.convert(context, from, to, amount)));
    }

    @Override
    public void onSuccess() {
    }

    @Override
    public void onError(String s) {
        mView.showError(s);
    }
}
