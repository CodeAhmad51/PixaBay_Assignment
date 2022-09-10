package com.example.pixabayassignment;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pixabayassignment.adapters.ImageAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class EnlargeImage extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enlarge_image);

        String url = getIntent().getStringExtra("url");

        imageView = findViewById(R.id.enlarge_image);
        imageView.setAdjustViewBounds(true);

        Picasso.get().load(url).into(imageView);

    }


}