package com.fourteenfourhundred.depict.views;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.Button;

import com.eclipsesource.v8.V8Object;
import com.fourteenfourhundred.depict.DepictAnnotations;

@DepictAnnotations.DepictComponent(name = "button")
public class DepictButtonView extends android.support.v7.widget.AppCompatButton{

    public DepictButtonView(Context context) {
        super(context);
    }


    @DepictAnnotations.DepictProperty(name = "value")
    public void text(String text){
        setText(text);
    }

}
