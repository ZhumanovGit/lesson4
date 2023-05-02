package ru.mirea.zhumanov.lesson4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.zhumanov.lesson4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView helloText = binding.textViewMirea;
        Button playButton = binding.buttonPlay;
        Button pauseButton = binding.buttonPause;
        Button nextButton = binding.buttonNext;




    }
}