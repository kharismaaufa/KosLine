package com.example.kosline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kosline.helper.Helper;

public class EditorActivity extends AppCompatActivity {

    private EditText editNama, editAlamat;
    private Button btn_Set;
    private Helper db = new Helper(this);
    private String id, nama, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        editNama = findViewById(R.id.edit_nama);
        editAlamat = findViewById(R.id.edit_alamat);
        btn_Set = findViewById(R.id.btn_Set);

        id = getIntent().getStringExtra("id");
        nama = getIntent().getStringExtra("nama");
        alamat = getIntent().getStringExtra("alamat");

        if (id == null || id.equals("")){
            setTitle("Tambah Kost");
        }else {
            setTitle("Edit Kost");
            editNama.setText(nama);
            editAlamat.setText(alamat);
        }

        btn_Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if (id == null || id.equals("")){
                        save();
                    }else {
                        edit();
                    }
                } catch (Exception e){
                    Log.e("Saving", e.getMessage());
                }
            }
        });
    }

    private void save(){
        if (String.valueOf(editNama.getText()).equals("") || String.valueOf(editAlamat.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan Lengkapi Data", Toast.LENGTH_SHORT).show();
        }else{
            db.insert(editNama.getText().toString(), editAlamat.getText().toString());
            finish();
        }
    }

    private void edit(){
        if (String.valueOf(editNama.getText()).equals("") || String.valueOf(editAlamat.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan Lengkapi Data", Toast.LENGTH_SHORT).show();
        }else{
            db.update(Integer.parseInt(id), editNama.getText().toString(), editAlamat.getText().toString());
            finish();
        }
    }
}