package com.fetchdatademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
private ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imgView = (ImageView) findViewById(R.id.imgView_fullscreen);
        Bundle bundle  = getIntent().getExtras();
        Picasso.with(DetailsActivity.this).load(bundle.getString("Link")).into(imgView);

    }
}
