package com.fourteenfourhundred.depict.views;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;

import com.fourteenfourhundred.depict.DepictAnnotations;


@DepictAnnotations.DepictComponent(name = "text")
public class DepictTextView extends android.support.v7.widget.AppCompatTextView{


    public DepictTextView(Context context) {
        super(context);
    }

    @DepictAnnotations.DepictProperty(name = "value")
    public void setValue(String text){
        this.setText(text);
    }

    @DepictAnnotations.DepictProperty(name = "color")
    public void setColor(String color){
        this.setTextColor(Color.parseColor(color));
    }



}

