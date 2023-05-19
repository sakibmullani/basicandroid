package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class secondFragment extends Fragment {

    View rootView;
    TextView getName, getNumber,getCity;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.second_fragment, container, false);

        getName=rootView.findViewById(R.id.getName);
        getNumber=rootView.findViewById(R.id.getNumber);
        getCity=rootView.findViewById(R.id.getCity);

        Bundle bundle=this.getArguments();

        String myName=bundle.getString("name");
        String myNumber= bundle.getString("number");
        String myCity=bundle.getString("city");
//        String s = myNumber.toString();

        getName.setText(myName);
        getNumber.setText(myNumber);
        getCity.setText(myCity);

        return rootView;
    }
}