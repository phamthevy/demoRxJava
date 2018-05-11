package com.example.vypt.demorxjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startfiltersActivity(View view) {
        startActivity(new Intent(MainActivity.this, filterActivity.class));
    }

    public void startbufferActivity(View view) {
        startActivity(new Intent(MainActivity.this, bufferActivity.class));
    }

    public void startmergeActivity(View view) {
        startActivity(new Intent(MainActivity.this, mergeActivity.class));
    }

    public void starttimerActivity(View view) {
        startActivity(new Intent(MainActivity.this, timerActivity.class));
    }

    public void startintervalActivity(View view) {
        startActivity(new Intent(MainActivity.this, intervalActivity.class));
    }

    public void startdistinceActivity(View view) {
        startActivity(new Intent(MainActivity.this, distinceActivity.class));
    }
}
