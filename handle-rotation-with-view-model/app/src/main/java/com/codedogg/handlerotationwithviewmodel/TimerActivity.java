package com.codedogg.handlerotationwithviewmodel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.Locale;

public class TimerActivity extends AppCompatActivity {

    private TimerViewModel viewModel;

    private TextView elapsedTimeView;
    private Button startButton;
    private Button pauseButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        initUI();

        viewModel = ViewModelProviders.of(this, new TimerViewModel.Factory()).get(TimerViewModel.class);

        observeElapsedTime();
        observeIsStarted();
    }

    private void initUI() {

        elapsedTimeView = findViewById(R.id.elapsed_time);

        startButton = findViewById(R.id.start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.start();
            }
        });

        pauseButton = findViewById(R.id.pause);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.pause();
            }
        });

        Button resetButton = findViewById(R.id.reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.reset();
            }
        });
    }

    private void observeElapsedTime() {

        viewModel.getElapsedTime().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double elapsedTime) {

                elapsedTimeView.setText(String.format(Locale.getDefault(), "%.1f", elapsedTime));
            }
        });
    }

    private void observeIsStarted() {

        viewModel.isStarted().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isStarted) {

                if (Boolean.TRUE.equals(isStarted)) {
                    startButton.setVisibility(View.GONE);
                    pauseButton.setVisibility(View.VISIBLE);
                } else {
                    startButton.setVisibility(View.VISIBLE);
                    pauseButton.setVisibility(View.GONE);
                }
            }
        });
    }
}
