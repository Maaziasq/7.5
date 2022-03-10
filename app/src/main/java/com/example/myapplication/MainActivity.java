package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import java.io.*;
import java.util.*;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Context context = null;

    EditText tnimi;
    EditText tekstikenttä;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        System.out.println("KANSION SIJAINTI "+context.getFilesDir());

        tnimi = findViewById(R.id.tnimi);
        tekstikenttä = findViewById(R.id.tekstikenttä);
    }

    public void readFile(View v){
        try{
            InputStream ins = context.openFileInput(String.valueOf(tnimi.getText()));

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = "";
            String data = "";
            while((s = br.readLine()) != null){
                data = data + s;
            }
            tekstikenttä.setText(data);
            ins.close();

        } catch (IOException e){
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("LUETTU");
        }
    }

    public void writeFile(View v){
        try{
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(String.valueOf(tnimi.getText()), Context.MODE_PRIVATE));

            String s = "";

            s = String.valueOf(tekstikenttä.getText());

            ows.write(s);
            ows.close();

        } catch (IOException e){
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("KIRJOITETTU");
        }
    }

}