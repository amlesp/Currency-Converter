package com.popovic.currencyconverter.data.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.popovic.currencyconverter.data.models.CurrencyModel;

@Database(entities = CurrencyModel.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = AppDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "exchange";

    private static final Object LOCK = new Object();
    private static volatile AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, AppDatabase.DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        Log.d(TAG, "Get database instance");
        return sInstance;
    }

    public abstract DataDao dataDao();
}
