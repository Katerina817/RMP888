package com.example.rmp888.UI.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.rmp888.Data.DataSources.Files.AppSpDS;
import com.example.rmp888.Data.DataSources.Files.CommonFDS;
import com.example.rmp888.Data.DataSources.Room.Entities.User;
import com.example.rmp888.Data.DataSources.SP.SPDS;
import com.example.rmp888.R;
import com.example.rmp888.UI.ViewModel.MyViewModel;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_CODE = 1;
    private MyViewModel viewModel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();

        Button button1=findViewById(R.id.AppSp);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //создаем текстовый файл в app-specific storage
                viewModel2=new MyViewModel(context, Name.APP);
                /*String fileName = "App.txt";
                String fileContent = "Пример содержимого файла";
                AppSpDS AppFile=new AppSpDS(context,fileName);*/
                viewModel2.writeApp("Пример содержимого файла");
                viewModel2.readAPP();
            }
        });

        Button button2=findViewById(R.id.Common);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // есть ли разрешение на запись во внешнее хранилище
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        Manifest.permission.READ_EXTERNAL_STORAGE
                                },
                                PERMISSION_CODE);
                    } else {
                        // Если есть разрешение
                        /*String fileName = "Common.txt";
                        String fileContent = "Пример содержимого файла2";
                        CommonFDS CommonFile=new CommonFDS(context,fileName);*/
                        viewModel2=new MyViewModel(context, Name.COMMON);
                        viewModel2.writeCommon("Пример содержимого файла2");
                        viewModel2.readCommon();
                    }
                }
            }
        });

        Button button3=findViewById(R.id.Shared);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*String fileName = "SharedPref.txt";
                SPDS shared=new SPDS(context,fileName);*/
                viewModel2=new MyViewModel(context, Name.SHARED);
                viewModel2.writeString("1","Кролик");
                viewModel2.writeString("1","Кролик2");
                String stroka1=viewModel2.readString("1");
                String stroka2=viewModel2.readString("2");
                Log.i("Shared",stroka1);
                Log.i("Shared",stroka2);
            }
        });
        /*Button button4=findViewById(R.id.vvesti);//ok для ввода в хранилище
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                        "SharedPreferences", Context.MODE_PRIVATE);
                EditText editText=findViewById(R.id.edittext);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("stringstroka", editText.getText().toString());
                editor.apply();
                Button button3=findViewById(R.id.Shared);
                String username = sharedPref.getString("stringstroka", "");
                button3.setText(username);
            }
        });*/
        Button button5=findViewById(R.id.DBase);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        viewModel2=new MyViewModel(context, Name.DBASE);
                        User user = new User();
                        user.firstName = "Vasya";
                        user.lastName = "Ivanov";
                        viewModel2.insertUser(user);
                        viewModel2.getAll();
                    }
                }).start();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            //предоставлено ли разрешение
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                viewModel2=new MyViewModel(getApplicationContext(), Name.COMMON);
                viewModel2.writeCommon("Пример содержимого файла2");
                viewModel2.readCommon();
            }
        }
    }
}