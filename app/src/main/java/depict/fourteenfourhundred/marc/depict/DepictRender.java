package depict.fourteenfourhundred.marc.depict;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
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
import com.eclipsesource.v8.V8Value;

import org.json.JSONObject;

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

    ArrayList<DepictValue> valueHandler = new ArrayList<DepictValue>();

    public DepictRender(Context context, V8 runtime){
        this.activity = (Activity) context;
        this.runtime = runtime;
        this.objectMap = runtime.getObject("propertyMap");
    }

    public void initComponent(V8Object component){
        this.component = component.twin();
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
                view = new DepictButtonView(activity,element,component.twin());
                break;
        }

        view = parseParameters(view,element);
       // Log.e("dd",(view==null)+"");
        return view;
    }




    class DepictValue {

        View view;
        String key;
        V8Object element;

        public DepictValue(View view, String key, V8Object element){
            this.view = view;
            this.key = key;
            this.element = element;
        }

    }


    public Object registerValues(View view,Object value,V8Object element){

        this.values = component.getObject("value");

                //component.executeObjectFunction("values",new V8Array(runtime));
        if(value instanceof String){
            String newValue = value.toString();
            Pattern pattern = Pattern.compile("\\{(.*?)\\}");
            Matcher m = pattern.matcher(newValue);
            while (m.find()) {
                newValue = newValue.replace(m.group(),values.getString(m.group(1)));

                valueHandler.add(new DepictValue(view,m.group(1),element));
            }
            return newValue;
        }else{
            return value;
        }

    }

    public void valueChange(String key){

        //Object value = data.get("value");
        //String key = data.getString("key");

       // Log.e("f","here??");

        V8Object params = new V8Object(runtime);


        for(final DepictValue val: valueHandler){
            if(val.key.equals(key)){


                //do something later to make only certain properties get updated
                val.view = parseParameters(val.view,val.element);


                return;
            }
        }
    }


    public View parseParameters(View view, V8Object element){



            for(String key: element.getKeys()) {

                if (key.equals("is") || key.equals("with") || key.equals("onClick")) continue;

                Object value = element.get(key);



                int transformationType = objectMap.getObject(key).getType("android");


                value = registerValues(view,value,element);

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
