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

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DepictRender {

    Activity activity;
    V8 runtime;
    V8Object objectMap;
    V8Object component;
    V8Object values;

    public DepictRender(Context context, V8 runtime){
        this.activity = (Activity) context;
        this.runtime = runtime;
        this.objectMap = runtime.getObject("propertyMap");



    }

    public void initComponent(V8Object component){
        this.component = component;
        this.values = component.executeObjectFunction("values",new V8Array(runtime));
        V8Object obj = component.executeObjectFunction("depict",new V8Array(runtime));
        depict(obj);
    }

    public void depict(V8Object element){
        FrameLayout frame = activity.findViewById(R.id.main);
        frame.addView(depictElement(element));
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

    public Object registerValues(Object value){
        //runtime.


        if(value instanceof String){
            String newValue = value.toString();
            Pattern pattern = Pattern.compile("\\{(.*?)\\}");
            Matcher m = pattern.matcher(newValue);
            while (m.find()) {
                newValue = newValue.replace(m.group(),values.getString(m.group(1)));
            }
            return newValue;
        }else{
            return value;
        }

    }


    public View parseParameters(View view, V8Object element){



            for(String key: element.getKeys()) {

                if (key.equals("is") || key.equals("with") || key.equals("onClick")) continue;

                Object value = element.get(key);



                int transformationType = objectMap.getObject(key).getType("android");


                value = registerValues(value);

                if (transformationType == V8Object.V8_ARRAY){



                    Class paramClass = value.getClass();


                    if(paramClass.equals(String.class)){
                        paramClass=CharSequence.class;

                    }else if(paramClass.equals(Integer.class)){
                        paramClass=int.class;
                    }



                    try {
                        view.getClass().getMethod(objectMap.getObject(key).getArray("android").getString(0),paramClass).invoke(view,value);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }



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
