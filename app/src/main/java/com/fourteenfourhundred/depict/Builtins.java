package com.fourteenfourhundred.depict;

import android.content.Context;
import android.util.Log;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Object;

import depict.fourteenfourhundred.marc.depict.R;

public class Builtins {

    public static void register(V8 runtime,Context context){
        BuitlinFunctions functions = new BuitlinFunctions(context);
        runtime.registerJavaMethod(functions,"print","print",new Class<?>[]{String.class});


        V8Object internals = new V8Object(runtime);
        runtime.add("depict",internals);

        internals.registerJavaMethod(functions,"initComponent","initComponent",new Class<?>[]{V8Object.class});
    }



    public static class BuitlinFunctions{

        Context context;

        public BuitlinFunctions(Context context){
            this.context = context;
        }

        public void print(String string){
            Log.e("Depict",string);
        }
        public void initComponent(V8Object component){
            DepictRender depiction = new DepictRender(context, component);
        }
    }

}
