package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.widget.TooltipCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    Button btn;
    View rootView;
    private boolean isButtonHeld = false;
    private Handler holdHandler;
    private Runnable holdRunnable;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.home_fragment, container, false);

        btn=rootView.findViewById(R.id.btn_ToolTip);
        toolKit_show();

        return  rootView;


    }

    private void toolKit_show() {


        // Set the tooltip for the button
        TooltipCompat.setTooltipText(btn, "Your tooltip text");

        holdHandler = new Handler();
        holdRunnable = new Runnable() {
            @Override
            public void run() {
                if (isButtonHeld && isAdded()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        TooltipCompat.setTooltipText((View) btn.getTooltipText(), (CharSequence) btn);
                    }
                }
            }
        };

        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Button is being held
                        isButtonHeld = true;
                        holdHandler.postDelayed(holdRunnable, 2000); // Adjust the hold time as needed (in milliseconds)
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        // Button is released
                        isButtonHeld = false;
                        holdHandler.removeCallbacks(holdRunnable);
                        break;
                }
                return false;
            }
        });

    }

}