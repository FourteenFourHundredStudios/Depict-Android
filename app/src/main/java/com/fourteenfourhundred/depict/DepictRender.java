package com.fourteenfourhundred.depict;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

import java.lang.reflect.Method;

import depict.fourteenfourhundred.marc.depict.R;

public class DepictRender {

    V8Object component;
    Activity activity;

    public DepictRender(Context context, V8Object component){
        this.component = component;
        this.activity = (Activity) context;

        FrameLayout frame = activity.findViewById(R.id.main);
        frame.addView(depictElement(component.executeObjectFunction("depict",new V8Array(component.getRuntime()))).view);
    }

    public DepictComponentView depictElement(V8Object element){
        DepictComponentView wrapper = ComponentHandler.createComponent(element.getString("is"));
        wrapper.setComponent(element);
        wrapper = setProperties(wrapper,element);
        wrapper.initEvents();
        return wrapper;
    }


    public DepictComponentView setProperties(DepictComponentView wrapper,V8Object element){
        for(String key: element.getKeys()) {
            if (key.equals("is") || key.equals("with") || key.equals("onClick")) continue;
            Object value = element.get(key);
            try {
                wrapper.properties.get(key).invoke(wrapper.view,value);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return wrapper;
    }



}
