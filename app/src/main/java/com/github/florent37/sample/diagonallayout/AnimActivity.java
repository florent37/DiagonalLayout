package com.github.florent37.sample.diagonallayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AnimActivity extends AppCompatActivity {

    View layoutTop;
    View layoutBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_simple);

        layoutTop = findViewById(R.id.layoutTop);
        layoutBottom = findViewById(R.id.layoutBottom);
    }
}
