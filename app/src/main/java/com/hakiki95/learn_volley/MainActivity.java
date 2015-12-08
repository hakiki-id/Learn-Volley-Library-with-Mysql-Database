package com.hakiki95.learn_volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tvData ;

    RequestQueue RequestData ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = (TextView) findViewById(R.id.tvData);

        RequestData = Volley.newRequestQueue(getApplicationContext());

        final  String Url = "http://192.168.155.2/praktikum/ParserData.php";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, Url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray dataArray = response.getJSONArray("datamahasiswa");

                            for (int i= 0 ; i < dataArray.length() ; i++) {
                                JSONObject mahasiswa = dataArray.getJSONObject(i);

                                String npm = mahasiswa.getString("npm");
                                String nama = mahasiswa.getString("nama");
                                String prodi = mahasiswa.getString("prodi");

                                tvData.append("npm : " + npm + "\n" +
                                        "nama : " + nama + "\n" +
                                        "prodi : "+ prodi + "\n" +
                                        "---------" + "\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.append("VOlley Erroorr ");
                    }
                }

        );

        RequestData.add(jsonRequest);
    }
}
