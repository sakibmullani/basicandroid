package com.example.myapplication.api_calling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

import it.sephiroth.android.library.xtooltip.Tooltip;


public class ToolTip_activity_Main extends AppCompatActivity {

    Button button, CustomButton;
    private ToolTipWindow customTooltip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tooltip);

        button = findViewById(R.id.id_toolTip);
        CustomButton=findViewById(R.id.id_toolTip2);

        customTooltip = new ToolTipWindow(ToolTip_activity_Main.this);

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                setToolTip(button, "ToolTip display");

                return true;

            }
        });

        CustomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onClick2(view);
            }
        });


    }



    public void onClick2(View view) {

        if (!customTooltip.isTooltipShown()){
            customTooltip.showToolTip(view);
        }
    }

    void setToolTip(View view, String text) {

        Tooltip tooltip = new Tooltip.Builder(ToolTip_activity_Main.this)
                .anchor(view, 0, 0, true)
                .activateDelay(800)
                .text(text)
                .maxWidth(500)
                .arrow(true)
                .floatingAnimation(Tooltip.Animation.Companion.getSLOW())
                .showDuration(2000)
                .fadeDuration(200)
                .overlay(true)
                .create();

        tooltip.show(view, Tooltip.Gravity.BOTTOM,true );


        /*tooltip.

                Tooltip.make(this,
                        new Tooltip.Builder(101)
                                .anchor(view, Tooltip.Gravity.BOTTOM)
                                .closePolicy(new Tooltip.ClosePolicy()
                                        .insidePolicy(true, false)
                                        .outsidePolicy(true, false), 300)
                                .activateDelay(800)
                                .showDelay(100)
                                .text(text)
                                .maxWidth(500)
                                .withArrow(true)
                                .withOverlay(true)
                                .floatingAnimation(Tooltip.AnimationBuilder.DEFAULT)
                                .build()).show();*/


    }


}