package com.fourteenfourhundred.depict;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.io.InputStream;

public class Util {

    public static String readRawRes(Context context, int resId){
        try {
            Resources res = context.getResources();
            InputStream input = res.openRawResource(resId);
            byte[] b = new byte[input.available()];
            input.read(b);
            return new String(b);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



}
