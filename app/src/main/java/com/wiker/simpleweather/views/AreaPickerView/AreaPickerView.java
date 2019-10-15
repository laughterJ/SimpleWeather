package com.wiker.simpleweather.views.AreaPickerView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wiker.framework.util.ToastUtil;
import com.wiker.simpleweather.R;
import com.wiker.simpleweather.adapter.AreaAdapter;
import com.wiker.simpleweather.model.CityModel;
import com.wiker.simpleweather.model.CityModel.City;
import com.wiker.simpleweather.model.ProvinceModel;
import com.wiker.simpleweather.model.ProvinceModel.Province;
import com.wiker.simpleweather.model.TownModel;
import com.wiker.simpleweather.model.TownModel.Town;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;

public class AreaPickerView extends Dialog implements OnResponseListener {

    private Context mContext;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    // View 集合
    private List<View> views;
    // TabLayout 标识
    private List<String> tabNames;
    // 省级数据
    private ProvinceModel mProvinceModel;
    private List<Province> provinceList;
    // 市级数据
    private CityModel mCityModel;
    private List<City> cityList;
    // 县级数据
    private TownModel mTownModel;
    private List<Town> townList;

    private ViewPagerAdapter mPagerAdapter;
    private AreaAdapter provinceAdapter, cityAdapter, townAdapter;
    private OnCompleteListener mListener;

    // 选中区域下标标记
    private int provinceSelectedItemId = -1;
    private int citySelectedItemId = -1;
    private int townSelectedItemId = -1;

    public AreaPickerView(@NonNull Context context, OnCompleteListener mListener) {
        this(context, 0, mListener);
    }

    public AreaPickerView(@NonNull Context context, int themeResId, OnCompleteListener mListener) {
        super(context, themeResId);
        this.mContext = context;
        this.mListener = mListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area_picker_content);
        Window window = getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(params);
        }
        initView();
        initData();
    }

    @SuppressWarnings("unchecked")
    private void initView() {
        mTabLayout = findViewById(R.id.tab_indic);
        mViewPager = findViewById(R.id.vp_list);

        View provincePage = LayoutInflater.from(mContext).inflate(R.layout.area_picker_list, null, false);
        ListView lvProvince = provincePage.findViewById(R.id.lv_area);
        lvProvince.setEmptyView(provincePage.findViewById(R.id.tv_empty_view));
        View cityPage = LayoutInflater.from(mContext).inflate(R.layout.area_picker_list, null, false);
        ListView lvCity = cityPage.findViewById(R.id.lv_area);
        lvCity.setEmptyView(cityPage.findViewById(R.id.tv_empty_view));
        View townPage = LayoutInflater.from(mContext).inflate(R.layout.area_picker_list, null, false);
        ListView lvTown = townPage.findViewById(R.id.lv_area);
        lvTown.setEmptyView(townPage.findViewById(R.id.tv_empty_view));
        views = new ArrayList<>();
        views.add(provincePage);
        views.add(cityPage);
        views.add(townPage);

        tabNames = new ArrayList<>();
        tabNames.add("省");
        tabNames.add("市");
        tabNames.add("县");

        mPagerAdapter = new ViewPagerAdapter();
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        lvProvince.smoothScrollToPosition(provinceSelectedItemId == -1 ? 0 : provinceSelectedItemId);
                        break;
                    case 1:
                        lvCity.smoothScrollToPosition(citySelectedItemId == -1 ? 0 : citySelectedItemId);
                        break;
                    case 2:
                        lvTown.smoothScrollToPosition(townSelectedItemId == -1 ? 0 : townSelectedItemId);
                        break;
                }
            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        provinceList = new ArrayList<>();
        provinceAdapter = new AreaAdapter((List)provinceList, AreaAdapter.TYPE_PROVINCE);
        lvProvince.setAdapter(provinceAdapter);
        lvProvince.setOnItemClickListener((parent, view, position, id) -> {
            mPagerAdapter.setPageTitle(0, provinceList.get(position).getProvinceName());
            mPagerAdapter.setPageTitle(1, "市");
            mPagerAdapter.setPageTitle(2, "县");
            provinceSelectedItemId = position;
            mCityModel.getCityData(provinceList.get(position).getProvinceId(), AreaPickerView.this);
            mViewPager.setCurrentItem(1, true);
        });

        cityList = new ArrayList<>();
        cityAdapter = new AreaAdapter((List)cityList, AreaAdapter.TYPE_CITY);
        lvCity.setAdapter(cityAdapter);
        lvCity.setOnItemClickListener((parent, view, position, id) -> {
            if (tabNames.get(0).equals("省")) {
                mPagerAdapter.setPageTitle(0, "北京");
            }
            mPagerAdapter.setPageTitle(1, cityList.get(position).getCityName());
            mPagerAdapter.setPageTitle(2, "县");
            citySelectedItemId = position;
            mTownModel.getTownData(cityList.get(position).getCityId(), AreaPickerView.this);
            mViewPager.setCurrentItem(2, true);
        });

        townList = new ArrayList<>();
        townAdapter = new AreaAdapter((List)townList, AreaAdapter.TYPE_TOWN);
        lvTown.setAdapter(townAdapter);
        lvTown.setOnItemClickListener((parent, view, position, id) -> {
            if (tabNames.get(0).equals("省")) {
                mPagerAdapter.setPageTitle(0, "北京");
            }
            if (tabNames.get(1).equals("市")) {
                mPagerAdapter.setPageTitle(0, "北京市");
            }
            mPagerAdapter.setPageTitle(2, townList.get(position).getTownName());
            mListener.onComplete(townList.get(position).getTownName());
        });
    }

    private void initData() {
        mProvinceModel = new ProvinceModel();
        mProvinceModel.getProvinceData(this);
        mCityModel = new CityModel();
        mCityModel.getCityData(1, this);
        mTownModel = new TownModel();
        mTownModel.getTownData(1, this);
    }

    @Override
    public void onSuccess(List<Object> dataList, int dataType) {
        if (dataType == AreaAdapter.TYPE_PROVINCE) {
            provinceList.clear();
            for (Object obj : dataList) {
                provinceList.add((Province) obj);
            }
            provinceAdapter.notifyDataSetChanged();
        }else if (dataType == AreaAdapter.TYPE_CITY) {
            cityList.clear();
            for (Object obj : dataList) {
                cityList.add((City) obj);
            }
            cityAdapter.notifyDataSetChanged();
        }else if (dataType == AreaAdapter.TYPE_TOWN) {
            townList.clear();
            for (Object obj : dataList) {
                townList.add((Town) obj);
            }
        }
    }

    @Override
    public void onError(BmobException e) {
        ToastUtil.showShortToast(e.getMessage());
    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames.get(position);
        }

        public void setPageTitle(int position, String title) {
            if (position >= 0 && position < tabNames.size()) {
                tabNames.set(position, title);
                notifyDataSetChanged();
            }
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(views.get(position));
            Log.e("AreaPickerView", "instantiateItem");
            return views.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(views.get(position));
            Log.e("AreaPickerView", "destroyItem");
        }
    }
}
