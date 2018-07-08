package depict.fourteenfourhundred.marc.depict;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

import java.lang.reflect.Method;

public class DepictRender {

    Activity activity;
    V8Object objectMap;

    public DepictRender(Context context,V8Object objectMap){
        this.activity = (Activity) context;
        this.objectMap = objectMap;
        //Log.e("g",objectMap.getObject("value").getString("type"));
    }

    public void depict(V8Array params){
        for(int i=0; i<params.length();i++){
            V8Object element = params.getObject(i);

            FrameLayout frame = activity.findViewById(R.id.main);
            frame.addView(depictElement(element));
        }
    }

    public View viewFromIs(V8Object element){
        View view = null;

        switch (element.getString("is")){
            case "layout":
                view = new LinearLayout(activity);
                break;
            case "text":
                view = new DepictTextView(activity,element);
                break;
            case "textInput":
                view = new EditText(activity);
                break;
            case "button":
                view = new DepictButtonView(activity,element);
                break;
        }

        view = parseParameters(view,element);
       // Log.e("dd",(view==null)+"");
        return view;
    }


    /*
    public void objMapper (){
        map("setText",value)
        map("orientation", option())
    }*/



    public View parseParameters(View view, V8Object element){

        try {

            for(String key: element.getKeys()) {

                if (key.equals("is") || key.equals("with") || key.equals("onClick")) continue;

                //Log.e("few",key+"");

               // V8Object value = element.getObject(key);
                Object value = element.get(key);

                //Log.e("f",value.toString());

                int transformationType = objectMap.getObject(key).getType("android");




                if (transformationType == V8Object.V8_ARRAY){



                    Class paramClass = value.getClass();


                    if(paramClass.equals(String.class)){
                        paramClass=CharSequence.class;
                    }else if(paramClass.equals(Integer.class)){
                        paramClass=int.class;
                    }

                    //new LinearLayout(activity).setOrientation(0);


                    /*
                    for (Method m : view.getClass().getMethods()){
                        if(m.getName().equals("setOrientation")){
                            Log.e("FOUD",(m.getParameterTypes()[0].toString())+"");
                        }
                    }
*/


                    view.getClass().getMethod(objectMap.getObject(key).getArray("android").getString(0),paramClass).invoke(view,value);
                }



            }
        }catch (Exception e){
            //e.printStackTrace();
            Log.e("ERROR",e.getMessage());
        }



        return view;
    }
/*
    public View parseParameters(View view, V8Object element){

        for(String key: element.getKeys()){
            if(key.equals("is") || key.equals("with")) continue;
            String value = element.getString(key);
            switch (key){
                case "orientation":

                    if(value.equals("vertical")){
                        ((LinearLayout) view).setOrientation(LinearLayout.VERTICAL);
                    }else if(value.equals("horizontal")){
                        ((LinearLayout) view).setOrientation(LinearLayout.HORIZONTAL);
                    }
                    break;
                case "value":
                    ((TextView)view).setText(value);
                    break;
                case "hint":
                    ((EditText)view).setHint(value);
                    break;
            }
        }

        return view;
    }
*/
    public View depictElement(V8Object element){

        View elementView = viewFromIs(element);


        if(element.contains("with")){
            V8Array params = element.getArray("with");
            for(int i=0; i<params.length();i++){
                ((ViewGroup)elementView).addView(depictElement(params.getObject(i)));
            }
        }
        return elementView;
    }

}
