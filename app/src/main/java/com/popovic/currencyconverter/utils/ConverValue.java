package com.popovic.currencyconverter.utils;

import android.content.Context;

import com.popovic.currencyconverter.data.database.AppDatabase;

public class ConverValue {

    /**Calculating exchange result,
     * Query database on mainthread because we don't want to allow user
     * to navigate to other task before calculation finish
     * */
    public static double convert(Context context, String from, String to, Double amount) {
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        return amount
                * appDatabase.dataDao().getCurrencyByCode(from).getBuyingRate()
                / appDatabase.dataDao().getCurrencyByCode(to).getSellingRate();
    }
}
