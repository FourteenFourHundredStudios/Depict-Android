package com.fourteenfourhundred.depict;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;

public class DepictComponentView {

    public Class<?> className;
    public V8Object component;
    public Context context;
    public View view;
    public HashMap<String, Method> properties = new HashMap<String, Method>();

    public DepictComponentView(Class<?> className, Context context){
        this.className = className;
        this.context = context;
        try {
            Constructor<?> constructor=className.getConstructor(Context.class);
            this.view = (View) constructor.newInstance(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        initProperties();
    }

    public void setComponent(V8Object component){
        this.component = component;
    }

    public void initProperties(){
        for (Method method : className.getDeclaredMethods()){
            if(method.isAnnotationPresent(DepictAnnotations.DepictProperty.class)) {
                DepictAnnotations.DepictProperty annotation = method.getAnnotation(DepictAnnotations.DepictProperty.class);
                properties.put(annotation.name(),method);
            }
        }
    }

    public void initEvents(){
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                component.executeJSFunction("onClick");

            }
        };
        view.setOnClickListener(listener);

    }


}
