package com.example.onlinepdfbookreaderapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PoemFragment extends Fragment {

    ProgressBar progressBar;
    ListView listView;

    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    HashMap <String,String> hashMap = new HashMap();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_poem, container, false);
        if(container!=null)
        {
            container.removeAllViews();
        }

        progressBar = myView.findViewById(R.id.progressBarId);
        listView = myView.findViewById(R.id.listViewId);

        String url = "https://rakibalam.000webhostapp.com/apps/poem.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {

                progressBar.setVisibility(View.GONE);

                try {

                    for(int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String book_name = jsonObject.getString("book_name");
                        String writter_name = jsonObject.getString("writter_name");
                        String book_des = jsonObject.getString("book_des");
                        String image_url = jsonObject.getString("image_url");

                        hashMap = new HashMap<>();
                        hashMap.put("book_name",book_name);
                        hashMap.put("writter_name",writter_name);
                        hashMap.put("book_des",book_des);
                        hashMap.put("image_url",image_url);
                        arrayList.add(hashMap);


                    }

                    MyAdapter myAdapter = new MyAdapter();
                    listView.setAdapter(myAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                progressBar.setVisibility(View.GONE);

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);


        return myView;
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View myview = layoutInflater.inflate(R.layout.sample_view, viewGroup, false);
            ImageView imageView = myview.findViewById(R.id.imageItemCoverId);
            TextView bookName = myview.findViewById(R.id.bookNameId);
            TextView writerName = myview.findViewById(R.id.writterNameId);
            TextView bookDes = myview.findViewById(R.id.bookDesId);


            HashMap<String, String> hashMap = arrayList.get(position);
            String book_name = hashMap.get("book_name");
            String writter_name = hashMap.get("writter_name");
            String book_des = hashMap.get("book_des");
            String image_url = hashMap.get("image_url");

            bookName.setText(book_name);
            writerName.setText(writter_name);
            bookDes.setText(book_des);

            Picasso.get()
                    .load(image_url)
                    .into(imageView);


            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            bookName.setBackgroundColor(color);


            return myview;
        }

    }
}