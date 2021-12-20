package com.example.kosline;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.kosline.adapter.Adapter;
import com.example.kosline.helper.Helper;
import com.example.kosline.model.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class KostActivity extends AppCompatActivity {

    ListView listView;
    Button btnAdd;
    AlertDialog.Builder dialog;
    List<Data> list = new ArrayList<>();
    Adapter adapter;
    Helper db = new Helper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kost);

        db = new Helper(getApplicationContext());
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KostActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        setTitle("List Users");
        listView = findViewById(R.id.list_item);
        adapter = new Adapter(KostActivity.this, list);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final  String id = list.get(i).getId();
                final String name = list.get(i).getName();
                final String email = list.get(i).getEmail();
                final  CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(KostActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(KostActivity.this, EditorActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("name", name);
                                intent.putExtra("email", email);
                                startActivity(intent);
                                break;
                            case 1:
                                db.delete(Integer.parseInt(id));
                                list.clear();
                                getData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getData();
    }

    private void getData(){
        ArrayList<HashMap<String, String>> rows = db.getAll();
        for (int i = 0; i<rows.size(); i++){
            String id = rows.get(i).get("id");
            String name = rows.get(i).get("name");
            String email = rows.get(i).get("email");

            Data data = new Data();
            data.setId(id);
            data.setName(name);
            data.setEmail(email);
            list.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    protected void onResume(){
        super.onResume();
        list.clear();
        getData();
    }
}