package com.gd70.android.a17bce2183.utils.dbutils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gd70.android.a17bce2183.models.CommodityModel;
import com.gd70.android.a17bce2183.utils.dbutils.daos.CommodityDao;

import static com.gd70.android.a17bce2183.utils.Constants.Database_Name;

@Database(entities = CommodityModel.class,exportSchema = false,version = 1)
public abstract class CommodityDatabase extends RoomDatabase{
    private static CommodityDatabase commodityDatabase;

    public CommodityDatabase() { }

    public static CommodityDatabase getCommodityDatabase(Context context) {
        if(commodityDatabase==null)
            commodityDatabase=Room.databaseBuilder(context.getApplicationContext(),CommodityDatabase.class,Database_Name)
                    .fallbackToDestructiveMigration()
                    .build();
        return commodityDatabase;
    }

    public abstract CommodityDao commodityDao();
}
