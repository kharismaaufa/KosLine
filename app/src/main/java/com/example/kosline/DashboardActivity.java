package com.example.kosline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {
    private FirebaseUser firebaseUser;
    private Button btnLogout;
    private TextView textname, btnMaps, btnTambahKost, btnTentangKami, btnbantuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        textname = findViewById(R.id.name);
        btnLogout = findViewById(R.id.btn_logout);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser.getDisplayName()!=null){
            textname.setText(firebaseUser.getDisplayName());
        }else{
            textname.setText("Login Gagal");
        }

        //Tombol Lihat Peta
        btnMaps = findViewById(R.id.maps);
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, MapsActivity.class));
            }
        });

        //Tombol Tambah Kost
        btnTambahKost = findViewById(R.id.tambahKos);
        btnTambahKost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, ListKos.class));
            }
        });

        //Tombol Tentang Kami
        btnTentangKami = findViewById(R.id.tentangkami);
        btnTentangKami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TentangActivity.class));
            }
        });

        //Tombol Bantuan
        btnbantuan = findViewById(R.id.bantuan);
        btnbantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BantuanActivity.class));
            }
        });


        btnLogout.setOnClickListener(v ->{
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });
    }
}