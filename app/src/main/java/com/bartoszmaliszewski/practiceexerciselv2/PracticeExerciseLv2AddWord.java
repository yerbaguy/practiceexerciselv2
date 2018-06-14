package com.bartoszmaliszewski.practiceexerciselv2;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import javax.net.ssl.HttpsURLConnection;
import static android.R.attr.value;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
public class PracticeExerciseLv2AddWord extends AppCompatActivity {
    EditText editTextEngWord, editTextPlWord;
    TextView textViewEngWord, textViewPlWord;
    Button btnSubmit;
    TextView textViewPracticeExercise;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_exercise_lv2_add_word);
        editTextEngWord = (EditText) findViewById(R.id.editTextEngWord);
        editTextPlWord = (EditText) findViewById(R.id.editTextPlWord);
        textViewPracticeExercise = (TextView) findViewById(R.id.textViewPracticeExercise);
//        textViewEngWord = (TextView) findViewById(R.id.textViewEngWord);
//        textViewPlWord = (TextView) findViewById(R.id.textViewPlWord);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        db = new DatabaseHelper(this);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String engWord = editTextEngWord.getText().toString();
                String plWord = editTextPlWord.getText().toString();
                if (engWord.matches("") && plWord.matches("")) {
                    Toast.makeText(getApplicationContext(), "You should type in both polish and english words", Toast.LENGTH_LONG).show();
                } else {
//                    textViewEngWord.setText(engWord);
//                    textViewPlWord.setText(plWord);
                    JSONObject postDataParams = new JSONObject();
                    try {
                        postDataParams.put("engword", engWord);
                        postDataParams.put("plword", plWord);
                        Log.e("params", postDataParams.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //  new SendJsonDataToServer().execute(postDataParams.toString());
                    db.addWord(engWord, plWord);
                    editTextEngWord.setText("");
                    editTextPlWord.setText("");
                }
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
    public void addWord(View view) {
        String engWord = editTextEngWord.getText().toString();
        String plWord = editTextPlWord.getText().toString();
        textViewEngWord.setText(engWord);
        textViewPlWord.setText(plWord);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//        if (id == R.id.practice) {
//
//            Intent intent_practice_exercise = new Intent(this, PracticeExerciseLv2.class);
//            startActivity(intent_practice_exercise);
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    class SendJsonDataToServer extends AsyncTask<String, String, String> {
        private static final String TAG = "asdfasd";
        @Override
        protected String doInBackground(String... params) {
            String JsonResponse = null;
            String JsonDATA = params[0];
            // HttpURLConnection urlConnection = null;
            //BufferedReader reader = null;
            //StringBuffer chaine = new StringBuffer("");
            try {
                //URL url = new URL("http://10.0.2.2:8080/PracticeExercise/mymethods/getidofengword");
                //  URL url = new URL("http://10.0.2.2:8080/PracticeExercise/mymethods/getidofengword");
                //          URL url = new URL("http://10.0.2.1:8080/PracticeExercise/mymethods/getidofengword");
                //       URL url = new URL("http://10.0.2.2:8080/PracticeExercise/mymethods/getidofengword");
                //          URL url = new URL("http://10.0.2.2:8080/Practice/rest/json/info/post");
                //      URL url = new URL("http://10.0.2.2:8080/PracticeExercise/rest/json/metallica/post"); here
                URL url = new URL("http://10.0.2.2:8080/PracticeExercise/rest/json/metallica/postwords");
                //          URL url = new URL("http://10.0.2.2:8080/PracticeWords/mymethods/post");
                //         URL url = new URL("http://10.0.2.2:8080/PracticeRest/rest/wordservice/postengword"); //here
                //      JSONObject postDataParams = new JSONObject();
                //      postDataParams.put("engword", JsonDATA);
                //      Log.e("params", postDataParams.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Length", "" + String.valueOf(JsonDATA));
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                //     JSONObject postDataParams = new JSONObject();
                //     postDataParams.put("engword", JsonDATA);
                //     Log.e("params", postDataParams.toString());
                // OutputStream os = conn.getOutputStream();
                // BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                //     wr.writeBytes(postDataParams.toString());
                wr.writeBytes(JsonDATA);
                wr.flush();
                wr.close();
                // writer.write(getPostDataString(postDataParams));
                // writer.write(params[0]);
                // writer.flush();
                // writer.close();
                // os.close();
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    return sb.toString();
                } else {
                    return new String("false:" + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception:" + e.getMessage());
            }
            //return null;
        }
        @Override
        protected void onProgressUpdate(String... params) {
            //String engword = params[0];
            String engword = params[2];
            //     textViewTypedEnglishWord.setText(engWord);
            // textViewTypedEngWord.setText(engword);
        }
        @Override
        //  protected void onPostExecute(String... results) {
        protected void onPostExecute(String result) {
            //super.onPostExecute(result);
            //String engWord = results[1];
            //        String result = results[0];
            //      textViewTypedEnglishWord.setText(engWord);
            //  textViewTypedEngWord.setText(engWord);
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }
        public String getPostDataString(JSONObject params) throws JSONException, UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();
            boolean first = true;
            Iterator<String> itr = params.keys();
            while (itr.hasNext()) {
                String key = itr.next();
                Object value = params.get(key);
                if (first)
                    first = false;
                else
                    result.append("&");
                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));
            }
            return result.toString();
        }
    }
}

