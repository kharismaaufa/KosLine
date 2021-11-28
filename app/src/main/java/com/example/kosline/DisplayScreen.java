package com.example.kosline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_info);

        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        String display = preferences.getString("display", "");

        TextView displayInfo = (TextView)  findViewById(R.id.tvName);
        displayInfo.setText(display);

        //Tombol Ke Dashboard
        Button toDashboard = (Button) findViewById(R.id.btnDashboard);
        toDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDashboard = new Intent(DisplayScreen.this, DashboardActivity.class);
                startActivity(toDashboard);
            }
        });

        //Tombol LogOut
        Button btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LogOut = new Intent(DisplayScreen.this, MainActivity.class);
                startActivity(LogOut);
            }
        });

        //Tombol Hapus Data
        Button hapusData = (Button) findViewById(R.id.btnClearData);
        hapusData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();

                Intent toMain = new Intent(DisplayScreen.this, MainActivity.class);
                startActivity(toMain);
            }
        });
    }


}