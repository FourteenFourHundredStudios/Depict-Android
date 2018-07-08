package depict.fourteenfourhundred.marc.depict;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.Button;

import com.eclipsesource.v8.V8Object;

public class DepictButtonView extends android.support.v7.widget.AppCompatButton{

    V8Object self;

    public DepictButtonView(Context context, V8Object self) {
        super(context);
        this.self = self;
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            self.executeJSFunction("onClick",self);
        }
        return super.onTouchEvent(event);
    }

}
