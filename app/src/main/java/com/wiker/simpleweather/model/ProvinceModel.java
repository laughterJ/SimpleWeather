package com.wiker.simpleweather.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.annotations.SerializedName;
import com.wiker.framework.application.MyApplication;
import com.wiker.simpleweather.sql.CityDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ProvinceModel{

    public void getProvinceData(OnResponseListener mListerer) {
        CityDatabaseHelper cityDbHelper = new CityDatabaseHelper(MyApplication.getInstance(), "city.db", null, 1);
        SQLiteDatabase db = cityDbHelper.getWritableDatabase();
        Cursor mCursor = db.query("Province", null, null, null, null, null, null);
        if (mCursor != null && mCursor.moveToFirst()){
            List<Province> provinceList = new ArrayList<>();
            do {
                Province mProvince = new Province();
                mProvince.setProvinceId(mCursor.getInt(mCursor.getColumnIndex("id")));
                mProvince.setProvinceName(mCursor.getString(mCursor.getColumnIndex("name")));
                mProvince.setProvinceCode(mCursor.getString(mCursor.getColumnIndex("areacode")));
                provinceList.add(mProvince);
            }while (mCursor.moveToNext());
            mListerer.onSuccess(provinceList);
        }else {
            BmobQuery<Province> mQuery = new BmobQuery<>();
            mQuery.findObjects(new FindListener<Province>() {
                @Override
                public void done(List<Province> list, BmobException e) {
                    if (e == null) {
                        ContentValues values = new ContentValues();
                        for (Province ele : list) {
                            values.put("id", ele.getProvinceId());
                            values.put("name", ele.getProvinceName());
                            values.put("areacode", ele.getProvinceCode());
                            db.insert("Province", null, values);
                        }
                        getProvinceData(mListerer);
                    }else {
                        mListerer.onError(e);
                    }
                }
            });
        }
    }

    public class Province extends BmobObject {
        @SerializedName("id")
        private int provinceId;
        @SerializedName("name")
        private String provinceName;
        @SerializedName("areacode")
        private String provinceCode;

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getProvinceCode() {
            return provinceCode;
        }

        public void setProvinceCode(String provinceCode) {
            this.provinceCode = provinceCode;
        }
    }

    public interface OnResponseListener {
        void onSuccess(List<Province> provinceList);
        void onError(BmobException e);
    }
}
