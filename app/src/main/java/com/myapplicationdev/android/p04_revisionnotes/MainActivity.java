package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnShow;
    EditText etNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.buttonInsertNote);
        etNote = findViewById(R.id.editTextNote);
        btnShow = findViewById(R.id.buttonShowList);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                RadioGroup rg = findViewById(R.id.radioGroupStars);
                int selectedButtonId = rg.getCheckedRadioButtonId();
                RadioButton rb = findViewById(selectedButtonId);
                Boolean exist = false;

                if (etNote.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(MainActivity.this, "Please enter a note", Toast.LENGTH_LONG).show();
                }else {
                    DBHelper db = new DBHelper(MainActivity.this);
                    ArrayList<Note> notes = db.getAllNotes();
                    if (notes.size() != 0) {
                        for (int i = 0; i < notes.size(); i++) {
                            Note currentNote = notes.get(i);
                            if (currentNote.getNoteContent().equalsIgnoreCase(etNote.getText().toString())) {
                                Toast.makeText(MainActivity.this, "This note already exist", Toast.LENGTH_LONG).show();
                                exist = true;
                            }
                        }
                    }
                    if (exist==false) {
                        db.insertNote(etNote.getText().toString(), Integer.parseInt(rb.getText().toString()));
                        Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                        etNote.setText("");
                        rg.clearCheck();
                        rg.check(R.id.radio1);
                    }
                    db.close();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), SecondActivity.class);
                startActivity(i);
            }
        });
    }
}
