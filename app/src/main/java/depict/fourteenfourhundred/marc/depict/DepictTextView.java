package depict.fourteenfourhundred.marc.depict;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;

import android.widget.TextView;

import com.eclipsesource.v8.V8Object;


public class DepictTextView extends android.support.v7.widget.AppCompatTextView{

    V8Object self;

    public DepictTextView(Context context, V8Object self) {
        super(context);
        this.self = self;
    }


    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            self.executeJSFunction("onClick");
            return performClick();
        }
        return true;
    }



}
