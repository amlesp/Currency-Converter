package com.popovic.currencyconverter.home;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.popovic.currencyconverter.data.database.AppDatabase;


import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<String> result = new MutableLiveData<>();
    private int spinnerFromSelection = 0;
    private int spinnerToSelection = 0;
    private LiveData<List<String>> codeList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        codeList = AppDatabase.getInstance(this.getApplication().getApplicationContext()).dataDao().getCurrencyCode();
    }

    public MutableLiveData<String> getResult() {
        return result;
    }

    public void setResult(MutableLiveData<String> result) {
        this.result = result;
    }

    public int getSpinnerFromSelection() {
        return spinnerFromSelection;
    }

    public void setSpinnerFromSelection(int spinnerFromSelection) {
        this.spinnerFromSelection = spinnerFromSelection;
    }

    public int getSpinnerToSelection() {
        return spinnerToSelection;
    }

    public void setSpinnerToSelection(int spinnerToSelection) {
        this.spinnerToSelection = spinnerToSelection;
    }

    public LiveData<List<String>> getCodeList() {
        return codeList;
    }
}
