package com.fourteenfourhundred.depict;

import android.content.Context;
import android.util.Log;

import com.fourteenfourhundred.depict.views.DepictButtonView;
import com.fourteenfourhundred.depict.views.DepictTextView;
import com.fourteenfourhundred.depict.views.DepictView;

import java.util.HashMap;

public class ComponentHandler {

    public static HashMap<String, DepictComponentView> componentMap = new HashMap<String, DepictComponentView>();
    public Context context;
    public static Class<?>[] componentClasses = {
        DepictTextView.class,

    };

    public static DepictComponentView createComponent(String name){
        return componentMap.get(name);
    }

    public static void registerComponents(Context context){
        for (Class<?> className : componentClasses){
            if(className.isAnnotationPresent(DepictAnnotations.DepictComponent.class)){
                DepictAnnotations.DepictComponent annotation = className.getAnnotation(DepictAnnotations.DepictComponent.class);
                componentMap.put(annotation.name(),new DepictComponentView(className,context));
            }
        }
    }




}
