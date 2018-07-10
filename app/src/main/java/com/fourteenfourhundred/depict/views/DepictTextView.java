package com.fourteenfourhundred.depict.views;

import android.content.Context;
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

    /*
    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            self.executeJSFunction("onClick",self);
        }
        return super.onTouchEvent(event);
    }*/



}

