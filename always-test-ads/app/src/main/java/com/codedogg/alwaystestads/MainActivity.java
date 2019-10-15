package com.codedogg.alwaystestads;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // load admob ad
        AdView admobBanner = findViewById(R.id.ad_view);
        admobBanner.loadAd(new AdRequest.Builder().build());
    }
}
