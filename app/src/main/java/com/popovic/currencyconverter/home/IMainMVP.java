package com.popovic.currencyconverter.home;

public interface IMainMVP {

    interface View {

        void showError(String error);

        void showResult(String result);
    }

    interface Presenter {

        void fetchData();

        void convert(String from, String to, Double amount);

    }
}


