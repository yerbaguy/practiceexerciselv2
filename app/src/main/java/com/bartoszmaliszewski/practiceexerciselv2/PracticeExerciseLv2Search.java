package com.bartoszmaliszewski.practiceexerciselv2;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
//import android.widget.Toolbar;
//import android.support.v7.widget.AppCompatButton;
//import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PracticeExerciseLv2Search extends AppCompatActivity {

   DatabaseHelper databaseHelper;



//    Intent intent = getIntent();
//    DatabaseHelper db = (DatabaseHelper) intent.getSerializableExtra("db");

 //   DatabaseHelper databaseHelper = getIntent().getExtras().getParcelable("dbhelper");

    ListView lv;
    SearchView sv;
    EditText engwordEditText, plwordEditText;
    Button saveBtn, retrieveBtn;
    CustomAdapter adapter;
    ArrayList<Word> words = new ArrayList<>();

   // DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_exercise_lv2_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        databaseHelper = new DatabaseHelper(this);

        lv = (ListView) findViewById(R.id.lv);
        sv = (SearchView) findViewById(R.id.sv);

        adapter = new CustomAdapter(this, words);

        this.getWords(null);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayDialog();
            }
        });

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String engword) {

                getWords(engword);

                return false;
            }
        });
    }

    private void displayDialog() {

        Dialog d = new Dialog(this);
        d.setTitle("SQLite Database");
        d.setContentView(R.layout.dialog_layout);

        engwordEditText = (EditText) d.findViewById(R.id.engwordEditText);
        plwordEditText = (EditText) d.findViewById(R.id.plwordEditText);
        saveBtn = (Button) d.findViewById(R.id.engwordEditText);
        retrieveBtn = (Button) d.findViewById(R.id.retrieveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                save(engwordEditText.toString(), plwordEditText.toString());
            }
        });

        retrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getWords(null);

            }
        });

        d.show();
    }


    private void save(String engword, String plword) {

      //  databaseHelper = new DatabaseHelper(this);

        databaseHelper.addWord(engword, plword);

      //  db.addWord(engword, plword);

        if (databaseHelper.addWord(engword, plword)) {
     //   if (db.addWord(engword, plword)) {

            engwordEditText.setText("");
            plwordEditText.setText("");
        } else {

            Toast.makeText(this, "Unable to save", Toast.LENGTH_SHORT).show();
        }
    }

    private void getWords(String searchTerm) {

       // databaseHelper = new DatabaseHelper(PracticeExerciseLv2Search.this);

        // searchTerm = "kind";

        words.clear();

        Word word = null;

        Cursor c= databaseHelper.retrieve(searchTerm);

      //  Cursor c = db.retrieve(searchTerm);

        while (c.moveToNext()) {

            int id = c.getInt(0);
            String engword = c.getString(1);

            System.out.println(id);
            System.out.println(engword);

            word = new Word();
            word.setId(id);
            word.setEngWord(engword);

            words.add(word);

            lv.setAdapter(adapter);
        }
    }
}
