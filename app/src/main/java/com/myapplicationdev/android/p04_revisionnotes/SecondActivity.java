package com.myapplicationdev.android.p04_revisionnotes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
	ListView lvNote;
	ArrayAdapter aa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		//TODO implement the Custom ListView

		DBHelper db = new DBHelper(this);
		db.getWritableDatabase();
		db.close();

		lvNote = this.findViewById(R.id.lv);

		ArrayList<Note> data =  db.getAllNotes();
		db.close();

		aa = new RevisionNotesArrayAdapter(this, R.layout.row, data);
		lvNote.setAdapter(aa);
	}
}
