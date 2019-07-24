package com.demoapp.View;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import com.demoapp.DetailActivity;
import com.demoapp.R;

public class DetailViewImplementer {

    private DetailActivity detailActivity;

    public DetailViewImplementer ( Context context ){

        detailActivity = (DetailActivity) context;
        detailActivity.setContentView(R.layout.activity_detail);

    }

    public void initViews (){

        TextView title = detailActivity.findViewById(R.id.title);
        TextView abs = detailActivity.findViewById(R.id.abstractT);
        TextView date = detailActivity.findViewById(R.id.date);
        TextView byLine = detailActivity.findViewById(R.id.byLine);
        TextView source = detailActivity.findViewById(R.id.source);

        if ( detailActivity.getIntent() != null ){

            Intent intent = detailActivity.getIntent();
            title.setText(intent.getStringExtra("title"));
            abs.setText(intent.getStringExtra("abs"));
            date.setText(intent.getStringExtra("date"));
            byLine.setText(intent.getStringExtra("byLine"));
            source.setText(intent.getStringExtra("source"));
        }

    }
}
