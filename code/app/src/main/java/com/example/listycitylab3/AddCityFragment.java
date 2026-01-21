package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {
    interface AddCityDialogListener{
        void addCity(City city);
        void editCity(City city, String name, String province);
    }
    private AddCityDialogListener listener;
    private Boolean edit;
    private City old_city;

    public AddCityFragment(City city) {
        this.edit=true;
        this.old_city=city;
    }

    public AddCityFragment(){
        this.edit=false;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        View view= LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city,null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add/Edit a city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Add",(dialog,which) ->{
                    String cityName= editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();
                    if(edit){
                        listener.editCity(old_city,cityName,provinceName);
                    } else{
                        listener.addCity(new City(cityName,provinceName));
                    }
                })
                .create();
    }
}
