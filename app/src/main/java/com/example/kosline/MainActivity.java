package com.example.kosline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMenuLogin = (Button) findViewById(R.id.Masuk);
        Button btnMenuRegister = (Button) findViewById(R.id.Daftar);

        btnMenuLogin.setOnClickListener(this);
        btnMenuRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Masuk:
                startActivity(new Intent(MainActivity.this, MasukActivity.class));
                break;
            case R.id.Daftar:
                startActivity(new Intent(MainActivity.this, DaftarActivity.class));
                break;
        }

    }

}