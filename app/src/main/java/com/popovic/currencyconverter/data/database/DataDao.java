package com.popovic.currencyconverter.data.database;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.popovic.currencyconverter.data.models.CurrencyModel;

import java.util.List;

@Dao
public interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCurrency(CurrencyModel currencyModel);

    @Query("SELECT currencyCode FROM currency")
    LiveData<List<String>> getCurrencyCode();

    @Query("SELECT * FROM currency WHERE currencyCode =:code")
    CurrencyModel getCurrencyByCode(String code);
}
