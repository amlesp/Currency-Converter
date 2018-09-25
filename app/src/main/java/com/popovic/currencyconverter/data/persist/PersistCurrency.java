package com.popovic.currencyconverter.data.persist;

import android.content.Context;
import com.popovic.currencyconverter.data.database.AppDatabase;
import com.popovic.currencyconverter.data.database.AppExecutors;
import com.popovic.currencyconverter.data.models.CurrencyModel;

import java.util.List;

public class PersistCurrency {

    /*Saving fresh currency model data to database, and removing old ones
    * **/
    public static void persist(Context context, List<CurrencyModel> data) {
        final AppDatabase db = AppDatabase.getInstance(context);
        AppExecutors appExecutors = AppExecutors.getInstance();
        db.clearAllTables();
        for (final CurrencyModel currencyModel : data) {
            appExecutors.diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    db.dataDao().insertCurrency(currencyModel);
                }
            });
        }
    }
}
