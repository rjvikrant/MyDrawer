package via.vik.mydrawer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import butterknife.Bind;

public class AboutUs extends AppCompatActivity {
    @Bind(R.id.about_us)
    MultiAutoCompleteTextView about_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("About Us");

    }

}
