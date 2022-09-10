package com.example.pixabayassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pixabayassignment.adapters.ImageAdapter;
import com.example.pixabayassignment.network.NetworkClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    FloatingActionButton feb_next , feb_previous;
    EditText editText;
    ImageView searchButton;
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    List<String> ImgUrl = new ArrayList<>();
    ImageAdapter adapter ;
    int page = 1;
    String query = "dog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkClient networkClient = new NetworkClient();

        requestQueue = Volley.newRequestQueue(this);

        if(savedInstanceState != null && savedInstanceState.containsKey("page")){
            query = savedInstanceState.getString("query");
            page = savedInstanceState.getInt("page");
        }

        networkClient.getData(query , page , this);
        recyclerView = findViewById(R.id.img);
        searchButton = findViewById(R.id.search_button);
        editText = findViewById(R.id.search_text_view);
        feb_next = findViewById(R.id.fab_next);
        feb_previous = findViewById(R.id.fab_previous);



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query = editText.getText().toString();
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                page = 1;
                networkClient.getData(query , page , MainActivity.this);

            }
        });

        feb_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                networkClient.getData(query , page , MainActivity.this);
            }
        });

        feb_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(page > 1){
                    page--;
                    networkClient.getData(query , page , MainActivity.this);
                }
            }
        });



    }

    public void updateUrls(List<String> imgUrl){

        this.ImgUrl = imgUrl;

        if (adapter == null){
            adapter = new ImageAdapter(ImgUrl,this);
            recyclerView.setAdapter(adapter);
        }
        else{
            adapter.updateUri(imgUrl);
        }


        int orientation = this.getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.setLayoutManager(new GridLayoutManager(this , 3) );
        }
        else{
            recyclerView.setLayoutManager(new GridLayoutManager(this , 2) );
        }

        adapter.notifyDataSetChanged();

    }



    public void addRequest(JsonObjectRequest request){
        this.requestQueue.add(request);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("page" , page);
        outState.putString("query" , query);
    }

}