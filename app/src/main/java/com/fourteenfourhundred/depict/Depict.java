package com.fourteenfourhundred.depict;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import depict.fourteenfourhundred.marc.depict.R;

public class Depict {

    Context context;
    String source;
    V8 runtime;

    public Depict(Context context,int initResId){
        this.context = context;
        this.source = Util.readRawRes(context,initResId);
        this.runtime = V8.createV8Runtime();

        loadDepictJsLib();
        Builtins.register(runtime,context);
        ComponentHandler.registerComponents(context);

        start();


    }


    public void loadDepictJsLib(){
        runtime.executeObjectScript(Util.readRawRes(context,R.raw.depict));
    }


    public void start(){
        runtime.executeObjectScript(source);
    }







}
