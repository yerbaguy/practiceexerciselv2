package com.bartoszmaliszewski.practiceexerciselv2;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
//import android.widget.Toolbar;
//import android.support.v7.widget.AppCompatButton;
//import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PracticeExerciseLv2Search extends AppCompatActivity {

   DatabaseHelper databaseHelper;



//    Intent intent = getIntent();
//    DatabaseHelper db = (DatabaseHelper) intent.getSerializableExtra("db");

 //   DatabaseHelper databaseHelper = getIntent().getExtras().getParcelable("dbhelper");

    ListView lv;
    SearchView sv;
    EditText engwordEditText, plwordEditText;
    TextView textViewPracticeExercise;
    TextView idTextView, engWordTextView, plWordTextView;
    Button saveBtn, retrieveBtn;
  //  CustomAdapter adapter;
    SimpleCursorAdapter adapter;

    String id, engword, plword;

    ArrayList<Word> words = new ArrayList<>();


//    final String[] from = new String[] {databaseHelper._id, databaseHelper.engword, databaseHelper.plword};
    final String[] from = new String[] {databaseHelper._id, databaseHelper.engword, databaseHelper.plword};


 //   final int[] to = new int[] {R.id.textViewId, R.id.textViewEngWord, R.id.textViewPlWord};
    final int[] to = new int[] {R.id.textViewId, R.id.textViewEngWord, R.id.textViewPlWord};



    // DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_exercise_lv2_search2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);



        databaseHelper = new DatabaseHelper(this);

        lv = (ListView) findViewById(R.id.lv);
        sv = (SearchView) findViewById(R.id.sv);

        textViewPracticeExercise = (TextView) findViewById(R.id.textViewPracticeExercise);

       // adapter = new CustomAdapter(this, words);

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

        textViewPracticeExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PracticeExerciseLv2.class);
                startActivity(intent);
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

//        while (c.moveToNext()) {
//
//            int id = c.getInt(0);
//            String engword = c.getString(1);
//
//            System.out.println(id);
//            System.out.println(engword);
//
//            word = new Word();
//            word.setId(id);
//            word.setEngWord(engword);
//
//            words.add(word);
//
//            lv.setAdapter(adapter);
//        }


        lv = (ListView) findViewById(R.id.lv);
        lv.setEmptyView(findViewById(R.id.empty));

      //  adapter = new SimpleCursorAdapter(this, R.layout.activity_view_layout, c, from, to, 0);
        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_layout, c, from, to, 0);

        adapter.notifyDataSetChanged();

        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView idTextView = (TextView) view.findViewById(R.id.textViewId);
                TextView engWordTextView = (TextView) view.findViewById(R.id.textViewEngWord);
                TextView plWordTextView = (TextView) view.findViewById(R.id.textViewPlWord);

                id = idTextView.getText().toString();
                engword = engWordTextView.getText().toString();
                plword = plWordTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyWordListActivity.class);

                modify_intent.putExtra("id", id);
                modify_intent.putExtra("engword", engword);
                modify_intent.putExtra("plword", plword);

                startActivity(modify_intent);

            }
        });




//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//
//                TextView idTextView = (TextView) view.findViewById(R.id.);
//                TextView engWordTextView = (TextView) view.findViewById(R.id.);
//                TextView plWordTextView = (TextView) view.findViewById(R.id.textViewPlWord);
//
//
//                String id = idTextView.getText().toString();
//                String engword = engWordTextView.getText().toString();
//                String plword = plWordTextView.getText().toString();
//
//                Intent modify_intent = new Intent(getApplicationContext(), ModifyWordListActivity.class);
//
//                modify_intent.putExtra("id", id);
//                modify_intent.putExtra("engword", engword);
//                modify_intent.putExtra("plword", plword);
//
//                startActivity(modify_intent);
//
//
//
//
//            }
//        });


    }
}
