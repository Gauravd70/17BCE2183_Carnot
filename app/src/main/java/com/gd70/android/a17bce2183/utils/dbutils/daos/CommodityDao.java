package com.gd70.android.a17bce2183.utils.dbutils.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gd70.android.a17bce2183.models.CommodityModel;

import java.util.List;

import static com.gd70.android.a17bce2183.utils.Constants.Table_Name;
import static com.gd70.android.a17bce2183.utils.Constants.districtKey;
import static com.gd70.android.a17bce2183.utils.Constants.marketKey;
import static com.gd70.android.a17bce2183.utils.Constants.stateKey;

@Dao
public interface CommodityDao {
    @Query("SELECT * FROM "+Table_Name)
    List<CommodityModel> getCommodities();

    @Insert
    void insertCommodity(CommodityModel commodityModel);

    @Update
    void updateCommodity(CommodityModel commodityModel);

    @Query("SELECT "+stateKey+" FROM "+Table_Name)
    List<String> getStates();

    @Query("SELECT "+districtKey+" FROM "+Table_Name+" WHERE "+stateKey+"= :state")
    List<String> getDistricts(String state);

    @Query("SELECT "+marketKey+" FROM "+Table_Name+" WHERE "+marketKey+"= :district")
    List<String> getMarkets(String district);
}
