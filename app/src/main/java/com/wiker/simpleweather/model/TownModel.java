package com.wiker.simpleweather.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.annotations.SerializedName;
import com.wiker.framework.application.MyApplication;

import com.wiker.simpleweather.adapter.AreaAdapter;
import com.wiker.simpleweather.sql.CityDatabaseHelper;
import com.wiker.simpleweather.views.AreaPickerView.OnResponseListener;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class TownModel extends BmobObject {

    @SuppressWarnings("unchecked")
    public void getTownData(int parentId, OnResponseListener mListener) {
        CityDatabaseHelper mCityDbHelper = new CityDatabaseHelper(MyApplication.getContext(),
                "city.db", null, CityDatabaseHelper.DB_VERSION);
        SQLiteDatabase db = mCityDbHelper.getWritableDatabase();
        Cursor mCursor = db.query("Town", null, "parentid = ?",new String[]{String.valueOf(parentId)},
                null, null, null, null);
        if (mCursor != null && mCursor.moveToFirst()) {
            List<Town> townList = new ArrayList<>();
            do {
                Town mTown = new Town();
                mTown.setTownId(mCursor.getInt(mCursor.getColumnIndex("id")));
                mTown.setTownName(mCursor.getString(mCursor.getColumnIndex("name")));
                mTown.setTownCode(mCursor.getString(mCursor.getColumnIndex("areacode")));
                mTown.setCityId(mCursor.getInt(mCursor.getColumnIndex("parentid")));
                townList.add(mTown);
            }while (mCursor.moveToNext());
            mListener.onSuccess((List)townList, AreaAdapter.TYPE_TOWN);
        }else {
            BmobQuery<Town> mQuery = new BmobQuery<>();
            mQuery.addWhereEqualTo("parentid", parentId)
                    .findObjects(new FindListener<Town>() {
                @Override
                public void done(List<Town> list, BmobException e) {
                    if (e == null) {
                        ContentValues values = new ContentValues();
                        for (Town ele : list) {
                            values.put("id", ele.getTownId());
                            values.put("name", ele.getTownName());
                            values.put("areacode", ele.getTownCode());
                            values.put("parentid", ele.getCityId());
                            db.insert("Town", null, values);
                            getTownData(parentId, mListener);
                        }
                    }else {
                        mListener.onError(e);
                    }
                }
            });
        }
    }

    public class Town extends BmobObject {

        @SerializedName("id")
        private int townId;
        @SerializedName("name")
        private String townName;
        @SerializedName("areacode")
        private String townCode;
        @SerializedName("parentid")
        private int cityId;
        private BmobRelation mCity;

        public int getTownId() {
            return townId;
        }

        public void setTownId(int townId) {
            this.townId = townId;
        }

        public String getTownName() {
            return townName;
        }

        public void setTownName(String townName) {
            this.townName = townName;
        }

        public String getTownCode() {
            return townCode;
        }

        public void setTownCode(String townCode) {
            this.townCode = townCode;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public BmobRelation getmCity() {
            return mCity;
        }

        public void setmCity(BmobRelation mCity) {
            this.mCity = mCity;
        }
    }
}
