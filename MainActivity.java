package com.example.amanbhardwaj.nasaapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //private String url = "http://api.openweathermap.org/data/2.5/weather?zip=76019,us&APPID=bab1364e8cda653ad3e2f29ab44035";
    private String url = "https://api.nasa.gov/neo/rest/v1/feed?start_date=2015-09-07&end_date=2015-09-07&api_key=HadmbrsKPNMM7XaJkrbvD7o0gXY9NzZu3XKTYazm";

    private Button getData;
    private ListView listData;
    private RequestQueue requestQueue;
    private ArrayList<String> mArrayAdapter;
    String[] items = new String[4];
    //JSONArray ja;
    JSONObject ja;
    Boolean main[] = new Boolean[10];
    Integer[] imgID = {R.drawable.x01,R.drawable.x02,R.drawable.x03,R.drawable.x04,R.drawable.x05,R.drawable.x06,R.drawable.x07,R.drawable.x08,R.drawable.x09,R.drawable.x10};
    String id[] = new String[10];
    //Boolean main1;
    String[] main1 = new String[10];
    String desc[] = new String[10];
    String[] linkArray = new String[10];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        getData = (Button) findViewById(R.id.getData);
        listData = (ListView) findViewById(R.id.listData);



        // listData.setAdapter(customeListView);


        final JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {


                            ja = response.getJSONObject("near_earth_objects");


                            JSONArray jsonObject = ja.getJSONArray("2015-09-07");


                            for(int i = 0;i<jsonObject.length();i++) {

                                JSONObject jsonObject1 = jsonObject.getJSONObject(i);


                                id[i] = jsonObject1.getString("name");
                                //  String main = jsonObject.getString("main");
                                // String desc = jsonObject.getString("description");
                                // String icon = jsonObject.getString("icon");
                                main[i] = jsonObject1.getBoolean("is_potentially_hazardous_asteroid");

                             /*   if (main1.equals("true")) {
                                    main[i] = "It is dangerous";
                                } else {
                                    main[i] = "It is not dangerous";
                                } */

                                //if(main[i].)

                                if(main[i])
                                {
                                    main1[i] = "It is dangerous";
                                }
                                else
                                {
                                    main1[i] = "It is not dangerous";
                                }

                                desc[i] = jsonObject1.getString("nasa_jpl_url");


                                //  Log.d("HERE", id);
                                //   Log.d("HERE", main);


                                items[0] = id[i];
                                // items[1] = main[i];
                                items[2] = desc[i];
                                // items[3] = icon;

                                linkArray[i] = desc[i];


                                //List<String> data_list = new ArrayList<>(Arrays.asList(items));
                                Log.d("HERE", "ARRAY");
                                Log.d("ID", id[i]);
                                Log.d("MAIN", main1[i]);
                              /*  ArrayAdapter mArrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                                        android.R.layout.simple_list_item_1, items);
                                listData.setAdapter(mArrayAdapter); */
                            }

                            CustomeListView customeListView = new CustomeListView(MainActivity.this, id, main1, desc, imgID);


                            listData.setAdapter(customeListView);

                            linkArray[0] = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";

                            listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkArray[position]));
                                    startActivity(browserIntent);
                                }
                            });






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");

                    }
                }
        );


        getData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                requestQueue.add(jor);
            }
        });


    }
}
