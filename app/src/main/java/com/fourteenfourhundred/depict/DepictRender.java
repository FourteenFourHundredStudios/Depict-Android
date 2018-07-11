package com.fourteenfourhundred.depict;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;
import com.fourteenfourhundred.depict.views.DepictLayoutView;

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
        wrapper = addChildren(wrapper,element);
        wrapper.initEvents();
        return wrapper;
    }

    public DepictComponentView addChildren(DepictComponentView wrapper,V8Object element) {
        if (!element.contains("with"))return wrapper;
        V8Array elements = element.getArray("with");
        for(int i=0;i<elements.length();i++){
            ((LinearLayout)wrapper.view).addView(depictElement(elements.getObject(i)).view);

        }
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
