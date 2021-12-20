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

    private EditText editName, editEmail;
    private Button btn_Set;
    private Helper db = new Helper(this);
    private String id, name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        editName = findViewById(R.id.edit_name);
        editEmail = findViewById(R.id.edit_email);
        btn_Set = findViewById(R.id.btn_Set);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");

        if (id == null || id.equals("")){
            setTitle("Tambah User");
        }else {
            setTitle("Edit User");
            editName.setText(name);
            editEmail.setText(email);
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
        if (String.valueOf(editName.getText()).equals("") || String.valueOf(editEmail.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan Lengkapi Data", Toast.LENGTH_SHORT).show();
        }else{
            db.insert(editName.getText().toString(), editEmail.getText().toString());
            finish();
        }
    }

    private void edit(){
        if (String.valueOf(editName.getText()).equals("") || String.valueOf(editEmail.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan Lengkapi Data", Toast.LENGTH_SHORT).show();
        }else{
            db.update(Integer.parseInt(id), editName.getText().toString(), editEmail.getText().toString());
            finish();
        }
    }
}