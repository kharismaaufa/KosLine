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

public class UpdateKos extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnSimpan, btnKembali;
    EditText namaKos, alamatKos, fasilitasKos, biayaKos, deskripsiKos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kos);

        dbHelper = new DataHelper(this);
        namaKos = (EditText) findViewById(R.id.namaKos);
        alamatKos = (EditText) findViewById(R.id.alamatKos);
        fasilitasKos = (EditText) findViewById(R.id.fasilitasKos);
        biayaKos = (EditText) findViewById(R.id.biayaKos);
        deskripsiKos = (EditText) findViewById(R.id.deskripsiKos);
        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        btnKembali = (Button) findViewById(R.id.btnKembali);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM datakos WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            namaKos.setText(cursor.getString(0).toString());
            alamatKos.setText(cursor.getString(1).toString());
            fasilitasKos.setText(cursor.getString(2).toString());
            biayaKos.setText(cursor.getString(3).toString());
            deskripsiKos.setText(cursor.getString(4).toString());
        }
        btnSimpan = (Button) findViewById(R.id.btnUpdate);
        // daftarkan even onClick pada btnSimpan
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE datakos set nama='"+
                        namaKos.getText().toString() +"', nama='" +
                        alamatKos.getText().toString() +"', alamat='" +
                        fasilitasKos.getText().toString()+"', fasilitas='"+
                        biayaKos.getText().toString() +"', biaya='" +
                        deskripsiKos.getText().toString() +"', deskripsi='" + "' WHERE nama='" +
                        namaKos.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Data Berhasil Diupdate!", Toast.LENGTH_LONG).show();
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
