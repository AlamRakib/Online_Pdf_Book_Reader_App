package com.example.onlinepdfbookreaderapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class NobelFragment extends Fragment {

    ProgressBar progressBar;
    ListView listView;

    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    HashMap <String,String> hashMap = new HashMap();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_novel, container, false);

        if(container!=null)
        {
            container.removeAllViews();
        }

        progressBar = myView.findViewById(R.id.progressBarId);
        listView = myView.findViewById(R.id.listViewId);


        String url = "https://rakibalam.000webhostapp.com/apps/bookview.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {

                progressBar.setVisibility(View.GONE);

                try {

                    for(int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String book_name = jsonObject.getString("bookName");
                        String writter_name = jsonObject.getString("writerName");
                        String book_des = jsonObject.getString("bookDes");
                        String image_url = jsonObject.getString("image");
                        String pdf_link = jsonObject.getString("pdf_link");


                        hashMap = new HashMap<>();
                        hashMap.put("bookName",book_name);
                        hashMap.put("writerName",writter_name);
                        hashMap.put("bookDes",book_des);
                        hashMap.put("image",image_url);
                        hashMap.put("pdf_link",pdf_link);

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

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
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
            View myview = layoutInflater.inflate(R.layout.sample_view, viewGroup,false);
            ImageView imageView = myview.findViewById(R.id.imageItemCoverId);
            TextView bookName = myview.findViewById(R.id.bookNameId);
            TextView writerName = myview.findViewById(R.id.writterNameId);
            TextView bookDes = myview.findViewById(R.id.bookDesId);
            LinearLayout linearLayout = myview.findViewById(R.id.layIteam);


            HashMap<String,String> hashMap = arrayList.get(position);
            String book_name =hashMap.get("bookName");
            String writter_name =hashMap.get("writerName");
            String book_des = hashMap.get("bookDes");
            String image_url = hashMap.get("image");
            String pdf_link = hashMap.get("pdf_link");


            bookName.setText(book_name);
            writerName.setText(writter_name);
            bookDes.setText(book_des);

            Picasso.get()
                    .load(image_url)
                    .into(imageView);


            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            bookName.setBackgroundColor(color);


            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent = new Intent(getActivity(),PdfViewerActivity.class);
                    PdfViewerActivity.pdf_link = pdf_link;
                    startActivity(intent);

                }
            });


            return myview;
        }
    }


}