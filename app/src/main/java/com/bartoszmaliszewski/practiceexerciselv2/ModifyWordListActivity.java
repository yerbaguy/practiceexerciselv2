package com.bartoszmaliszewski.practiceexerciselv2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//public class ModifyWordListActivity extends AppCompatActivity {

//public class ModifyWordListActivity extends Activity implements DialogInterface.OnClickListener {
public class ModifyWordListActivity extends Activity implements View.OnClickListener {

    private EditText engWord;
    private EditText plWord;
    private Button updateBtn, deleteBtn, cancelBtn;

   // private long _id;
    private int _id;

    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_modify_word_list);

        setTitle("Modify Record");

       // setContentView(R.layout.activity_modify_word_list);
        setContentView(R.layout.activity_modify_record);

        dbManager = new DBManager(this);
        dbManager.open();


        engWord = (EditText) findViewById(R.id.editTextEngWord);
        plWord= (EditText) findViewById(R.id.editTextPlWord);

        updateBtn = (Button) findViewById(R.id.btn_update);
        cancelBtn = (Button) findViewById(R.id.btn_cancel);

        Intent intent = getIntent();
      //  String id = intent.getStringExtra("textViewId");
        String id = intent.getStringExtra("id");
      //  String engword = intent.getStringExtra("textViewEngWord");
        String engword = intent.getStringExtra("engword");
      //  String plword = intent.getStringExtra("textViewPlWord");
        String plword = intent.getStringExtra("plword");

       // _id = Long.parseLong(id);

        _id = Integer.parseInt(id);


        engWord.setText(engword);
        plWord.setText(plword);



        updateBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);




    }

   // @Override
   // public void onClick(DialogInterface dialogInterface, int i) {

   // }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_update:

            String engword = engWord.getText().toString();
            String plword = plWord.getText().toString();

            dbManager.update(_id, engword, plword);
            //    dbManager.update(engword, plword);
            this.returnHome();
            break;

            case R.id.btn_cancel:

                Intent intent = new Intent(getApplicationContext(), PracticeExerciseLv2.class);
                startActivity(intent);

                break;

        }

    }

    public void returnHome() {

       // Intent home_intent = new Intent(getApplicationContext(), WordListActivity.class)
        Intent home_intent = new Intent(getApplicationContext(), PracticeExerciseLv2.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);

    }
}
