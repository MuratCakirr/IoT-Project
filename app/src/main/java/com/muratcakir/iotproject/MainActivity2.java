package com.muratcakir.iotproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    LineChart mpLineChart;
    ArrayList<Entry> dataVals;
    JSONObject feed;
    JSONArray jsonArray;
    public static int [] field = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.thingspeak.com/channels/1270744/fields/1.json?api_key=2HGWB2ZXSFJ73IGV&results=4";

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            jsonArray = response.getJSONArray("feeds");
                            for (int i = 0; i < jsonArray.length()+1; i++) {
                                feed = jsonArray.getJSONObject(i);
                                field[i] = feed.getInt("field1");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        datas1();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }

    public ArrayList<Entry> dataValues1(){
        dataVals = new ArrayList<Entry>();

        dataVals.add(new Entry(1,field[0]));
        dataVals.add(new Entry(2,field[1]));
        dataVals.add(new Entry(3,field[2]));
        dataVals.add(new Entry(4,field[3]));

        return  dataVals;
    }

    public void datas1(){
        mpLineChart = (LineChart) findViewById(R.id.line_chart);
        LineDataSet lineDataSet1=new LineDataSet(dataValues1(),"Data Set 1");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);

        LineData data = new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart.invalidate();
    }

    public void guncelle(View view){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.thingspeak.com/channels/1270744/fields/1.json?api_key=2HGWB2ZXSFJ73IGV&results=4";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            jsonArray = response.getJSONArray("feeds");
                            for (int i = 0; i < jsonArray.length()+1; i++) {
                                feed = jsonArray.getJSONObject(i);
                                field[i] = feed.getInt("field1");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        datas1();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(jsonObjectRequest);
    }

    public void sonuc(View view){
        Intent intent = getIntent();
        int min = intent.getIntExtra("min",0);
        int max = intent.getIntExtra("max",0);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);

        if(field[3]<min){
            builder.setTitle("Uyarı!");
            builder.setMessage("Kalp atışı değeriniz olması gerekenin altındadır." +
                    "\n(Güncel Kalp Atısı Değeriniz = " + field[3] + " BPM)" + "\n(İdeal Değer Aralığı = " + min + "-" + max + " BPM)");
            builder.setNegativeButton("Tamam", null);
            builder.show();
        }else if(field[3]>max){
            builder.setTitle("Uyarı!");
            builder.setMessage("Kalp atışı değeriniz olması gerekenin üstündedir." +
                    "\n(Güncel Kalp Atış Değeriniz = " + field[3] + " BPM)" + "\n(İdeal Değer Aralığı = " + min + "-" + max + " BPM)");
            builder.setNegativeButton("Tamam", null);
            builder.show();
        }else{
            builder.setTitle("Uyarı!");
            builder.setMessage("Kalp atısı değeriniz ideal değerdedir." +
                    "\n(Güncel Kalp Atış Değeriniz = " + field[3] + " BPM)" + "\n(İdeal Değer Aralığı = " + min + "-" + max + " BPM)");
            builder.setNegativeButton("Tamam", null);
            builder.show();
        }
    }
}