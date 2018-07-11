package com.fourteenfourhundred.depict.views;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;

import com.fourteenfourhundred.depict.DepictAnnotations;

@DepictAnnotations.DepictComponent(name = "layout")
public class DepictLayoutView extends LinearLayout{

    public DepictLayoutView(Context context) {
        super(context);
    }


    @DepictAnnotations.DepictProperty(name = "direction")
    public void orientation(int axis){
        this.setOrientation(axis);
    }





}
