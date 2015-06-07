package com.zhanghaitao.weather;

import java.util.ArrayList;
import java.util.List;

import db.CoolWeatherDB;

import model.City;
import model.County;
import model.Province;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Context mContext;
	private TextView title;
	private ListView listView;
	List<String> dataList = new ArrayList<String>();;
	List<Province> listProvinces;
	List<City> listCity;
	List<County> listCounty;
	ArrayAdapter<String> adapter;
	
	private static CoolWeatherDB coolWeatherDB;
	
	String[] strings = {"hello", "good", "yes", "no"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		
		
		coolWeatherDB = CoolWeatherDB.getInstance(mContext);
		for (int i=0;i<10;i++) {
//			Province province = new Province();
//			province.setProvinceName("sichuan"+i);
//			province.setProvinceCode("1000"+i);
//			coolWeatherDB.saveProvivce(province);
			
			City city = new City();
			city.setCityName("chengdu"+i);
			city.setCityCode("10"+i);
			city.setProvinceId(887);
			coolWeatherDB.saveCity(city);
			
			County county = new County();
			county.setCountyName("pixian"+i);
			county.setCountyCode("33"+i);
			county.setCityId(8);
			coolWeatherDB.saveCounty(county);
		}
		initViewData();
	}

	
	private void initViewData() {
		title = (TextView) findViewById(R.id.title_main);
		listView = (ListView) findViewById(R.id.listview_main);
		
		coolWeatherDB.deleteProvivce();
		listProvinces = coolWeatherDB.loadProvince();
		
		for (Province mProvince : listProvinces) {
			dataList.add(mProvince.getProvinceName());
		}
		
		coolWeatherDB.deleteCities();
		listCity = coolWeatherDB.loadCities(887);
		for (City citys: listCity) {
			dataList.add(citys.getCityName());
		}
		
		listCounty = coolWeatherDB.loadCounty(8);
		for (County countys: listCounty) {
			dataList.add(countys.getCountyName());
		}
		
		adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, dataList);
		listView.setAdapter(adapter);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
