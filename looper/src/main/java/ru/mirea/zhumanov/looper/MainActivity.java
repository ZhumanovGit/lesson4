package ru.mirea.zhumanov.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.zhumanov.looper.databinding.ActivityMainBinding;


public	class	MainActivity	extends	AppCompatActivity	{
    @Override
    protected	void	onCreate(Bundle	savedInstanceState)	{
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding	=	ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {

                Log.d(MainActivity.class.getSimpleName(), "Task execute. This is result: " + msg.getData().getString("result"));

            }
        };

        MyLooper	myLooper	=	new	MyLooper(mainThreadHandler);
        myLooper.start();

        binding.editTextMirea.setText("Мой номер по списку №6");
        binding.buttonMirea.setOnClickListener(new	View.OnClickListener()	{
            @Override
            public	void	onClick(View	v)	{
                int delay = Integer.parseInt(String.valueOf(binding.editTextAge.getText()));

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                    }
                }, delay);

                Log.d(MainActivity.class.getSimpleName(),	"Ваш возраст: " + binding.editTextAge.getText());
                Log.d(MainActivity.class.getSimpleName(),	"Ваша работа: " + binding.editTextTextWork.getText());

                Message	msg	=	Message.obtain();
                Bundle	bundle	=	new	Bundle();
                bundle.putString("KEY",	"mirea");
                msg.setData(bundle);
                myLooper.handler.sendMessage(msg);
            }
        });
    }
}