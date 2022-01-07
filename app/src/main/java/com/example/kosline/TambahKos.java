package com.example.kosline;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TambahKos extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnSimpan, btnKembali;
    EditText namaKos, alamatKos, fasilitasKos, biayaKos, deskripsiKos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kos);

        dbHelper = new DataHelper(this);
        namaKos = (EditText) findViewById(R.id.namaKos);
        alamatKos = (EditText) findViewById(R.id.alamatKos);
        fasilitasKos = (EditText) findViewById(R.id.fasilitasKos);
        biayaKos = (EditText) findViewById(R.id.biayaKos);
        deskripsiKos = (EditText) findViewById(R.id.deskripsiKos);
        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        btnKembali = (Button) findViewById(R.id.btnKembali);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("INSERT INTO datakos(nama, alamat, fasilitas, biaya, deskripsi) VALUES('" +
                        namaKos.getText().toString() + "','" +
                        alamatKos.getText().toString() + "','" +
                        fasilitasKos.getText().toString() + "','" +
                        biayaKos.getText().toString() + "','" +
                        deskripsiKos.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Data Berhasil Disimpan!", Toast.LENGTH_LONG).show();
                ListKos.lk.RefreshList();
                finish();
            }
        });

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListKos.class));
            }
        });
    }
}
