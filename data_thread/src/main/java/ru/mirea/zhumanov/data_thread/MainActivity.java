package ru.mirea.zhumanov.data_thread;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import ru.mirea.zhumanov.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding	=	ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.textView.setText("runOnUiThread - выполняет указанное действие в потоке пользовательского интерфейса. Если текущий поток является потоком пользовательского интерфейса, то действие выполняется немедленно," +
                " если текущий поток не является потоком пользовательского интерфейса, действие отправляется в очередь событий потока пользовательского интерфейса.\n" +
                "\n" +
                "postDelayed - приводит к добавлению выполняемого файла в очередь сообщений, он будет запущен по прошествию указанного времени.Файл запустится в потоме пользовательского интерфейса\n" +
                "\n" +
                "post - приводит к добавлению выполняемого файла в очередь сообщений. Исполняемый файл будет запущен в потоке пользовательского интерфейса.");

        final Runnable runn1 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("run 1");
            }
        };
        final Runnable runn2 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("run 2");
            }
        };
        final Runnable runn3 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("run 3");
            }
        };
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    binding.tvInfo.postDelayed(runn3, 2000);
                    binding.tvInfo.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}