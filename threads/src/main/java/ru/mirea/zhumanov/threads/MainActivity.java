package ru.mirea.zhumanov.threads;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import ru.mirea.zhumanov.threads.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private	int	counter	=	0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding	=	ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView infoTextView = findViewById(R.id.textView);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Имя текущего потока: " + mainThread.getName());

        mainThread.setName("МОЙ НОМЕР ГРУППЫ: 06, НОМЕР ПО СПИСКУ: 06, МОЙ ЛЮБИИМЫЙ ФИЛЬМ: ВЛАСТЕЛИН КОЛЕЦ");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(), "Stack: " + Arrays.toString(mainThread.getStackTrace()));

        binding.buttonMirea.setOnClickListener(new	View.OnClickListener()	{
            @Override
            public	void	onClick(View	v)	{

                new	Thread(new	Runnable()	{
                    public	void run()	{
                        int	numberThread = counter++;
                        Log.d("ThreadProject",	String.format("Запущен	поток:	№%d / Cтудентом	группы:	%s /	Номер	по списку:	%d	",	numberThread,	"БСБО-06-21",	6));
                        long	endTime	=	System.currentTimeMillis()	+	20	*	1000;
                        if(String.valueOf(binding.editText1.getText()).isEmpty()) {
                            throw new IllegalArgumentException("String is empty");
                        } else {
                            int twins = Integer.parseInt(String.valueOf(binding.editText1.getText()));
                            int days = Integer.parseInt(String.valueOf(binding.editText2.getText()));

                            if (days != 0 && twins !=0) {
                                Log.d("ThreadProject", "Среднее колличество пар:	" + twins / days);
                            }
                        }
                        while	(System.currentTimeMillis()	<	endTime)	{
                            synchronized	(this)	{
                                try	{
                                    wait(endTime	- System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(),	"Endtime:	"	+	endTime);
                                }	catch	(Exception	e)	{
                                    throw	new	RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject",	"Выполнен поток №	"	+	numberThread);
                        }
                    }
                }).start();

                if(String.valueOf(binding.editText1.getText()).isEmpty()) {
                    throw new IllegalArgumentException("String is empty");
                } else {
                    int twins = Integer.parseInt(String.valueOf(binding.editText1.getText()));
                    int days = Integer.parseInt(String.valueOf(binding.editText2.getText()));
                    if (days != 0 && twins != 0) {
                        Log.d("ThreadProject", "Среднее колличество пар:	" + twins / days);
                        binding.textView.setText("Среднее число пар: " + String.valueOf(twins / days));
                    }
                }

            }
        });
    }
}