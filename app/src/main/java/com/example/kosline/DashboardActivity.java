package com.example.kosline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        //Tombol Tambah Kost
        TextView btnTambahKost = (TextView) findViewById(R.id.tambahKos);
        btnTambahKost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toTambahKost = new Intent(DashboardActivity.this, KostActivity.class);
                startActivity(toTambahKost);
            }
        });

        //Tombol LogOut
        Button btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent LogOut = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(LogOut);
            }
        });
    }

}