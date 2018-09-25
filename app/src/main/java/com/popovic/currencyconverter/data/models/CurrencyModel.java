package com.popovic.currencyconverter.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "currency")
public class CurrencyModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("unit_value")
    private int unitValue;
    @SerializedName("selling_rate")
    private double sellingRate;
    @SerializedName("buying_rate")
    private double buyingRate;
    @SerializedName("median_rate")
    private double medianRate;
    @SerializedName("currency_code")
    private String currencyCode;

    @Ignore
    public CurrencyModel() {
    }

    public CurrencyModel(int unitValue, double sellingRate, double buyingRate, double medianRate, String currencyCode) {
        this.unitValue = unitValue;
        this.sellingRate = sellingRate;
        this.buyingRate = buyingRate;
        this.medianRate = medianRate;
        this.currencyCode = currencyCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(int unitValue) {
        this.unitValue = unitValue;
    }

    public double getSellingRate() {
        return sellingRate;
    }

    public void setSellingRate(double sellingRate) {
        this.sellingRate = sellingRate;
    }

    public double getBuyingRate() {
        return buyingRate;
    }

    public void setBuyingRate(double buyingRate) {
        this.buyingRate = buyingRate;
    }

    public double getMedianRate() {
        return medianRate;
    }

    public void setMedianRate(double medianRate) {
        this.medianRate = medianRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "CurrencyModel{" +
                "id=" + id +
                ", unitValue=" + unitValue +
                ", sellingRate=" + sellingRate +
                ", buyingRate=" + buyingRate +
                ", medianRate=" + medianRate +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}
