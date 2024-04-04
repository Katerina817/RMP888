package com.example.rmp888.Data.DataSources.Files;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommonFDS {
    private File file;
    private Context context;
    private String filename;
    public CommonFDS(Context context, String filename){
        this.context=context;
        this.filename=filename;
        this.file = new File(Environment.getExternalStorageDirectory(), filename);
        Log.i("CommonFDS","Создан экземпляр класса");
    }
    public void writeCommon(String stroka){
        try {
            FileOutputStream fileIn = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fileIn.write(stroka.getBytes());
            fileIn.close();
            Log.i("CommonFDS","Данные записаны в файл");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void readCommon(){
        try {
            FileInputStream fileOut = context.openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fileOut);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line=null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            Log.i("CommonFDS",stringBuilder+"");
            fileOut.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
