package com.muratcakir.iotproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;

public class MainActivity extends AppCompatActivity {

    CheckBox checkBox;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    int max=0;
    int min=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox = findViewById(R.id.checkBox);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);

    }

    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.checkBox:
                if (checked){
                    min=120;
                    max=140;
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                }

            else{
                    System.out.println("olmadi");
                }

                break;

            case R.id.checkBox1:
                if (checked){
                    min=90;
                    max=120;
                    checkBox.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                }
            else{
                    System.out.println("olmadi1");
                }
                break;

            case R.id.checkBox2:
                if (checked){
                    min=90;
                    max=100;
                    checkBox1.setChecked(false);
                    checkBox.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                }
                else{
                    System.out.println("olmadi2");
                }
                break;

            case R.id.checkBox3:
                if (checked){
                    min=80;
                    max=90;
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox.setChecked(false);
                    checkBox4.setChecked(false);
                }
                else{
                    System.out.println("olmadi3");
                }
                break;

            case R.id.checkBox4:
                if (checked){
                    min=60;
                    max=80;
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox.setChecked(false);
                }
                else{
                    System.out.println("olmadi4");
                }
                break;

        }
    }

    public void devam(View view){
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        intent.putExtra("min", min);
        intent.putExtra("max", max);
        startActivity(intent);
    }

}