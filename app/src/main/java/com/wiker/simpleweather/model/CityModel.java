package com.wiker.simpleweather.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.wiker.framework.application.MyApplication;
import com.wiker.simpleweather.adapter.AddressAdapter;
import com.wiker.simpleweather.sql.CityDatabaseHelper;
import com.wiker.simpleweather.views.AreaPickerView.OnResponseListener;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class CityModel extends BmobObject {

    @SuppressWarnings("unchecked")
    public void getCityData(int parentId, OnResponseListener mListener) {
        CityDatabaseHelper mCityDbHelper = new CityDatabaseHelper(MyApplication.getContext(),
                "city.db", null, CityDatabaseHelper.DB_VERSION);
        SQLiteDatabase db = mCityDbHelper.getWritableDatabase();
        Cursor mCursor = db.query("City", null, "parentid = ?",new String[]{String.valueOf(parentId)},
                null, null, null, null);
        if (mCursor != null && mCursor.moveToFirst()) {
            List<City> cityList = new ArrayList<>();
            do {
                City mCity = new City();
                mCity.setCityId(mCursor.getInt(mCursor.getColumnIndex("id")));
                mCity.setCityName(mCursor.getString(mCursor.getColumnIndex("name")));
                mCity.setCityCode(mCursor.getString(mCursor.getColumnIndex("areacode")));
                mCity.setProvinceId(mCursor.getInt(mCursor.getColumnIndex("parentid")));
                cityList.add(mCity);
            }while (mCursor.moveToNext());
            mListener.onSuccess((List) cityList, AddressAdapter.TYPE_CITY);
        }else {
            BmobQuery<City> mQuery = new BmobQuery<>();
            mQuery.addWhereEqualTo("parentid", parentId)
                    .findObjects(new FindListener<City>() {
                @Override
                public void done(List<City> list, BmobException e) {
                    if (e == null) {
                        ContentValues values = new ContentValues();
                        for (City ele : list) {
                            values.put("id", ele.getCityId());
                            values.put("name", ele.getCityName());
                            values.put("areacode", ele.getCityCode());
                            values.put("parentid", ele.getProvinceId());
                            db.insert("City", null, values);
                        }
                        getCityData(parentId, mListener);
                    }else {
                        mListener.onError(e);
                    }
                }
            });
        }
    }

    public class City extends BmobObject{
        @SerializedName("id")
        private int cityId;
        @SerializedName("name")
        private String cityName;
        @SerializedName("areacode")
        private String cityCode;
        @SerializedName("parentid")
        private int provinceId;
        private BmobRelation mProvince;

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public BmobRelation getmProvince() {
            return mProvince;
        }

        public void setmProvince(BmobRelation mProvince) {
            this.mProvince = mProvince;
        }
    }
}
