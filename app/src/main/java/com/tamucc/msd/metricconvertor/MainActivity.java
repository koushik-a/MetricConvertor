package com.tamucc.msd.metricconvertor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Metric Convertor");
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerView, new MetricConvertorFragment()).commit();

    }

    private void setSharedPreferences() {
        SharedPreferences.Editor editor = this.getPreferences(Context.MODE_PRIVATE).edit();
        editor.putFloat(getString(R.string.km),(float) 1.609);
        editor.putFloat(getString(R.string.meters),(float) 1609.34);
        editor.putFloat(getString(R.string.feet), (float) 5280);
        editor.putFloat(getString(R.string.inches), (float) 63360);
        editor.putFloat(getString(R.string.kilogram), (float) 0.453);
        editor.putFloat(getString(R.string.gram),(float) 453.592);
        editor.putFloat(getString(R.string.ounces), (float) 16);
        editor.putFloat(getString(R.string.degree),(float) -17.222);
        editor.putFloat(getString(R.string.kelvin),(float) 255.928);
        editor.putFloat(getString(R.string.euro),(float)0.91 );
        editor.putFloat(getString(R.string.indian_rupee), (float) 76.26);
        editor.putFloat(getString(R.string.australian_dollar),(float) 1.33);
        editor.putFloat(getString(R.string.canadian_dollar),(float) 1.25);
        editor.commit();
    }


}