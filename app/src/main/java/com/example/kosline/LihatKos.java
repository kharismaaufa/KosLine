package com.example.kosline;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LihatKos extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnKembali, btnMaps;
    TextView namaKos, alamatKos, fasilitasKos, biayaKos, deskripsiKos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kos);
        dbHelper = new DataHelper(this);
        namaKos = (TextView) findViewById(R.id.namaKos);
        alamatKos = (TextView) findViewById(R.id.alamatKos);
        fasilitasKos = (TextView) findViewById(R.id.fasilitasKos);
        biayaKos = (TextView) findViewById(R.id.biayaKos);
        deskripsiKos = (TextView) findViewById(R.id.deskripsiKos);
        btnKembali = (Button) findViewById(R.id.btnKembali);
        btnMaps = (Button) findViewById(R.id.btnMaps);

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
        btnKembali = (Button) findViewById(R.id.btnKembali);
        btnKembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            }
        });
    }
}
