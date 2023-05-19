package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Objects;


public class firstFragment extends Fragment implements View.OnClickListener {
    View rootView;
    EditText name, number,city;
    AppCompatButton submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      rootView= inflater.inflate(R.layout.first_fragment, container, false);
       initView();

        return rootView;
    }

    public  void initView(){

        name=rootView.findViewById(R.id.enterName);
        number=rootView.findViewById(R.id.enterMobile);
        city=rootView.findViewById(R.id.enterCity);
        submit=rootView.findViewById(R.id.submit);

        submit.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        String MobilePattern = "[0-9]{10}";

        if (view.getId()==R.id.submit){


            Bundle bundle=new Bundle();

            if(name.length()==0){

                name.requestFocus();
                name.setError("Name CANNOT BE EMPTY");
            }

            else if(!number.getText().toString().matches(MobilePattern)) {

                number.requestFocus();
                number.setError("phone number is Must be in 10 Digit");

            }
            else if(city.length()==0){

                city.requestFocus();
                city.setError("City CANNOT BE EMPTY");

            }

            else {
                String myName = name.getText().toString();
                String myNumber = number.getText().toString();
                String myCity = city.getText().toString();

                bundle.putString("name", myName);
                bundle.putString("number", myNumber);
                bundle.putString("city", myCity);


                secondFragment secondFragment = new secondFragment();
                secondFragment.setArguments(bundle);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, secondFragment).addToBackStack(null).commit();

//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.replace(R.id.container, new firstFragment());
//                fragmentTransaction.commit();

            }
        }

    }
}