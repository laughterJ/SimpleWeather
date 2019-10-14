package com.wiker.simpleweather.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wiker.framework.application.MyApplication;
import com.wiker.simpleweather.R;
import com.wiker.simpleweather.model.CityModel;
import com.wiker.simpleweather.model.ProvinceModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobObject;

public class AddressAdapter extends BaseAdapter {

    public static final int TYPE_PPOVINCE = 0;
    public static final int TYPE_CITY = 1;
    public static final int TYPE_TOWN = 2;

    private List<Object> dataList;
    private int dataType;

    public AddressAdapter(List<Object> dataList, int dataType) {
        this.dataList = dataList;
        this.dataType = dataType;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        String addressData = null;
        switch (dataType) {
            case TYPE_PPOVINCE:
                addressData = ((ProvinceModel.Province) dataList.get(position)).getProvinceName();
                break;
            case TYPE_CITY:
                addressData = ((CityModel.City) dataList.get(position)).getCityName();
                break;
            case TYPE_TOWN:
                break;
        }
        ViewHolder mHolder = null;
        if (itemView == null) {
            itemView = LayoutInflater.from(MyApplication.getContext())
                    .inflate(R.layout.item_address, parent, false);
            mHolder = new ViewHolder(itemView);
            itemView.setTag(mHolder);
        }else {
            mHolder = (ViewHolder) itemView.getTag();
        }
        mHolder.tvAddress.setText(addressData);
        return itemView;
    }

    static class ViewHolder {

        @BindView(R.id.tv_address) TextView tvAddress;

        ViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
