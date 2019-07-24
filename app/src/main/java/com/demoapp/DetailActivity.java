package com.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demoapp.View.DetailViewImplementer;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DetailViewImplementer detailViewImplementer = new DetailViewImplementer(this);
        detailViewImplementer.initViews();
    }
}
