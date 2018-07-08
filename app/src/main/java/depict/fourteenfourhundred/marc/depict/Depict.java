package depict.fourteenfourhundred.marc.depict;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

import java.io.InputStream;

public class Depict {

    Context context;
    String source;
    V8 runtime;
    V8Object initObject;

    public Depict(Context context,int initResId){
        this.context = context;
        this.source = readRawRes(initResId);
        this.runtime = V8.createV8Runtime();

        runtime.registerJavaMethod(new Printer(),"print","print",new Class<?>[]{String.class});

        runtime.executeObjectScript(readRawRes(R.raw.depict));

        runtime.registerJavaMethod(new DepictRender(context,runtime.getObject("propertyMap")),"depict","__depict__",new Class<?>[]{V8Array.class});



        initObject = runtime.executeObjectScript(source);

    }

    public static class Printer{
        public void print(String string){
            Log.e("Depict",string);
        }
    }


    public String readRawRes(int resId){
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
