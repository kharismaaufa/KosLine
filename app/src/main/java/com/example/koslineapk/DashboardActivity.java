package com.example.koslineapk;

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
    private TextView textname, btnTambahKost;

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

        //Tombol Tambah Kost
        btnTambahKost = findViewById(R.id.tambahKos);
        btnTambahKost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toTambahKost = new Intent(DashboardActivity.this, KostActivity.class);
                startActivity(toTambahKost);
            }
        });

        btnLogout.setOnClickListener(v ->{
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), MasukActivity.class));
            finish();
        });
    }
}