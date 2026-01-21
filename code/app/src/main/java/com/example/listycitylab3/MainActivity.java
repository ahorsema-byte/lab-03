package com.example.listycitylab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener {

    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;
    private City selection;
    private Boolean edit;

    @Override
    public void addCity(City city){
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }
    public void editCity(City city, String name, String province){
        city.setName(name);
        city.setProvince(province);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit=false;
        String[] cities = {
                "Edmonton", "Vancouver", "Toronto"
        };
        String[] provinces = {
                "AB","BC","ON"
        };

        dataList = new ArrayList<City>();
        for (int i=0; i<cities.length; i++){
            dataList.add(new City(cities[i],provinces[i]));
        }

        cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                selection=cityAdapter.getItem(position);
                edit=true;
            }
        });

        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(v-> {
            if(edit){
                new AddCityFragment(selection).show(getSupportFragmentManager(),"Edit City");
            } else {
                new AddCityFragment().show(getSupportFragmentManager(),"Add City");
            }
        });
    }
}