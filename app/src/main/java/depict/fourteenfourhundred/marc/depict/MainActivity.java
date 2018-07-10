package depict.fourteenfourhundred.marc.depict;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fourteenfourhundred.depict.Depict;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Depict(this,R.raw.init);


    }



}
