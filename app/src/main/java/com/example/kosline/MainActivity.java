package com.example.kosline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMenuLogin = (Button) findViewById(R.id.btnMenuLogin);
        Button btnMenuRegister = (Button) findViewById(R.id.btnMenuRegister);

        btnMenuLogin.setOnClickListener(this);
        btnMenuRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMenuLogin:
                Intent toLoginActivitiy = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(toLoginActivitiy);
                break;
            case R.id.btnMenuRegister:
                Intent toRegisterActivity = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(toRegisterActivity);
                break;
        }

    }

}