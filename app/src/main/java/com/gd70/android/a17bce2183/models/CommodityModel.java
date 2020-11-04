package com.gd70.android.a17bce2183.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import static com.gd70.android.a17bce2183.utils.Constants.Table_Name;
import static com.gd70.android.a17bce2183.utils.Constants.arrivalDateKey;
import static com.gd70.android.a17bce2183.utils.Constants.commodityKey;
import static com.gd70.android.a17bce2183.utils.Constants.districtKey;
import static com.gd70.android.a17bce2183.utils.Constants.marketKey;
import static com.gd70.android.a17bce2183.utils.Constants.maxPriceKey;
import static com.gd70.android.a17bce2183.utils.Constants.minPriceKey;
import static com.gd70.android.a17bce2183.utils.Constants.modalPriceKey;
import static com.gd70.android.a17bce2183.utils.Constants.stateKey;
import static com.gd70.android.a17bce2183.utils.Constants.timeStampKey;
import static com.gd70.android.a17bce2183.utils.Constants.varietyKey;

@Entity(tableName = Table_Name)
public class CommodityModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName(timeStampKey)
    private long timeStamp;
    @SerializedName(stateKey)
    private String state;
    @SerializedName(districtKey)
    private String district;
    @SerializedName(marketKey)
    private String market;
    @SerializedName(commodityKey)
    private String commodity;
    @SerializedName(varietyKey)
    private String variety;
    @SerializedName(arrivalDateKey)
    private String arrival_date;
    @SerializedName(minPriceKey)
    private double minPrice;
    @SerializedName(maxPriceKey)
    private double maxPrice;
    @SerializedName(modalPriceKey)
    private double modalPrice;

    @Ignore
    CommodityModel(){}

    public CommodityModel(int id, long timeStamp, String state, String district, String market, String commodity, String variety, String arrival_date, double minPrice, double maxPrice, double modalPrice) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.state = state;
        this.district = district;
        this.market = market;
        this.commodity = commodity;
        this.variety = variety;
        this.arrival_date = arrival_date;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.modalPrice = modalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getModalPrice() {
        return modalPrice;
    }

    public void setModalPrice(double modalPrice) {
        this.modalPrice = modalPrice;
    }
}
