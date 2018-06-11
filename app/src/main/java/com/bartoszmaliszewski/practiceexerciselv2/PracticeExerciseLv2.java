package com.bartoszmaliszewski.practiceexerciselv2;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class PracticeExerciseLv2 extends AppCompatActivity {

    TextView textViewPulledTheWord, textViewTypedEnglishWord, textViewAddWords, textViewTypedEngWord, textViewSupport, textViewRandom, textViewRandPl, textViewAnotherUpdate, textViewIdOfEngWord, textViewShowWord;
    Button btnPullTheWord, btnSubmitEngWord, btnSupport;
    EditText editTextEnglishWord;
    String engWord;
    Context context;


    DatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_exercise_lv2);



        editTextEnglishWord = (EditText) findViewById(R.id.editTextEnglishWord);
        btnSubmitEngWord = (Button) findViewById(R.id.btnSubmitEngWord);
        textViewTypedEnglishWord = (TextView) findViewById(R.id.textViewTypedEnglishWord);
        textViewTypedEngWord = (TextView) findViewById(R.id.textViewTypedEngWord);
        btnPullTheWord = (Button) findViewById(R.id.btnPullTheWord);
        textViewPulledTheWord = (TextView) findViewById(R.id.textViewPulledTheWord);
        textViewAddWords = (TextView) findViewById(R.id.textViewAddWords);
        btnSupport = (Button) findViewById(R.id.btnSupport);
        textViewSupport = (TextView) findViewById(R.id.textViewSupport);
        textViewRandom = (TextView) findViewById(R.id.textViewRandom);
        textViewRandPl = (TextView) findViewById(R.id.textViewRandPl);
        textViewAnotherUpdate = (TextView) findViewById(R.id.textViewAnotherUpdate);
        textViewIdOfEngWord = (TextView) findViewById(R.id.textViewIdOfEngWord);
        textViewShowWord = (TextView) findViewById(R.id.textViewShowWord);


        db = new DatabaseHelper(this);

        //db.addWord("kind", "rodzaj");

        int querycount =  db.countRows();
//        db.close();

//        System.out.println("count rows:" + querycount);


        int countrandom = db.countRandom();

//        System.out.println("count random:" + countrandom);

        int n1 = 4;
//        db.updateCountRandomIntoRandPl(n1);




        // int n = 1;
        // db.insertCountRandomIntoRandPl(n);


        //  System.out.println("GetPlWord:" + db.getPLWord());
//        System.out.println("GetIdOfPlWord:" + db.getIdOfPlWord());

        //   System.out.println("GetPlWord:" + db.getPLWord());
//        Log.d("db", "querycount");

//        Toast.makeText(this, "Values saved", Toast.LENGTH_LONG).show();




        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //new GetSupport().execute();

                String engword =  db.findEngWord();


                textViewSupport.setText(engword);

            }
        });


        textViewAddWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), PracticeExerciseLv2AddWord.class);
                startActivity(intent);

            }
        });



        btnSubmitEngWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // String engWord = editTextEnglishWord.getText().toString();
                String engword = editTextEnglishWord.getText().toString();

                // sendDataToServer(view, engWord);

                // textViewTypedEngWord.setText(engWord);

                int findidofengword = db.findIdOfEngWord(engword);

                int getidofplword = db.getIdOfPlWord();



                textViewIdOfEngWord.setText(Integer.toString(findidofengword));
                //  textViewIdOfEngWord.setText(Boolean.toString(db.compareEngAndPlWord()));


                if (findidofengword == getidofplword) {

                    Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(getApplicationContext(), "NO", Toast.LENGTH_LONG).show();
                }


                editTextEnglishWord.setText("");

                // db.findIdOfEngWord(engword);

                //   Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();


                //       if (db.compareEngAndPlWord()) {

                //           Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
                //       } else {

                //           Toast.makeText(getApplicationContext(), "NO", Toast.LENGTH_LONG).show();
                //       }


            }
        });




        btnPullTheWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //textViewPulledTheWord.setText("kljasdf");

                //System.out.println("GetPlWord:" + db.getPLWord());


                int querycount =  db.countRows();
//                db.close();

//                System.out.println("count rows:" + querycount);


                int countrandom = db.countRandom();

//                db.updateCountRandomIntoRandPl(countrandom);
                //db.updateSecondCountRandomIntoRandPl();

                int getidofplword = db.getIdOfPlWord();

                int getinfofromrandpl = db.getInfoFromRandPl();

                // int getidofengword = db.getIdOfPlWord();



                // db.anotherUpdate();


//                System.out.println("count random:" + countrandom);

                // int n = 1;
                // db.insertCountRandomIntoRandPl(n);


                //  System.out.println("GetPlWord:" + db.getPLWord());
//                System.out.println("GetIdOfPlWord:" + db.getIdOfPlWord());








//                System.out.println("GetPlWord:" + db.getPLWord());


//                textViewPulledTheWord.setText(db.getPLWord());

                String plword = db.getPLWord();
//                textViewPulledTheWord.setText(db.countRandom());
                //          textViewPulledTheWord.setText(Integer.toString(querycount));
                //          textViewPulledTheWord.setText(Integer.toString(countrandom));
                textViewPulledTheWord.setText(plword);
                textViewRandom.setText(Integer.toString(countrandom));
                textViewRandPl.setText(Integer.toString(getidofplword));
                textViewAnotherUpdate.setText(Integer.toString(getinfofromrandpl));
                //          textViewIdOfEngWord.setText(Integer.toString(getidofengword));
                //          textViewPulledTheWord.setText(Integer.toString(getidofplword));

                //      new GetPlWord().execute();

            }
        });

        textViewShowWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = new DatabaseHelper(context);

                Intent intent = new Intent(getApplicationContext(), PracticeExerciseLv2Search.class);
             //   Intent intent = new Intent(getApplicationContext(), PracticeExerciseLv2Search.class).putExtra("dbhelper", (CharSequence) db);
              //  intent.putExtra("db", String.valueOf(db));
                startActivity(intent);


            }
        });

        //     btnPullTheWord.setOnClickListener(new View.OnClickListener() {
        //         @Override
        //         public void onClick(View view) {


//                new GetPlWord().execute();

//            }
        //      });






//        db = new DatabaseHelper(this);

//        db.addWord("kind", "rodzaj");

//        int querycount =  db.countRows();
//        db.close();

//        System.out.println("count rows:" + querycount);


//        int countrandom = db.countRandom();

//        System.out.println("count random:" + countrandom);

        // int n = 1;
        // db.insertCountRandomIntoRandPl(n);


        //  System.out.println("GetPlWord:" + db.getPLWord());
//        System.out.println("GetIdOfPlWord:" + db.getIdOfPlWord());

//        System.out.println("GetPlWord:" + db.getPLWord());
//        Log.d("db", "querycount");

//        Toast.makeText(this, "Values saved", Toast.LENGTH_LONG).show();




    }

    public void sendDataToServer(View view, String engWord) {

        new SendJsonDataToServer().execute(engWord);
    }




    class GetSupport extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... params) {


            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;


            String plword = null;

            try {

                // URL url = new URL("http://10.0.2.2:8080/PracticeExercise/rest/json/metallica/selectedplword");
                URL url = new URL("http://10.0.2.2:8080/PracticeExercise/rest/json/metallica/getsupport");
                //    URL url = new URL("http://10.0.2.2:8080/PracticeExercise/rest/json/metallica/getengword");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();

                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {

                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while ((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {

                    return null;
                }

                plword = buffer.toString();

                Log.d("plword", "plword");

                return plword;



            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error", e);

                return null;

            } finally {

                if (urlConnection != null) {
                    urlConnection.disconnect();
                }

                if (reader != null) {

                    try {

                        reader.close();

                    } catch (final Exception e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }

                }

            }

            // return null;
        }


        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);

            // textViewPulledTheWord.setText(plword);
            //  textViewPulledTheWord.setText(s);
            textViewSupport.setText(s);

            //  Log.i("json", s);

        }


        @Override
        protected void onProgressUpdate(String... s) {

            //textViewPulledTheWord.setText(plword[1]);
            //  textViewPulledTheWord.setText("lkjasdf");
            //      textViewSupport.setText(s);
        }

    }




    class GetPlWord extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;


            String plword = null;

            try {

                // URL url = new URL("http://10.0.2.2:8080/PracticeExercise/rest/json/metallica/selectedplword");
                URL url = new URL("http://10.0.2.2:8080/PracticeExercise/rest/json/metallica/getinfo");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();

                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {

                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while ((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {

                    return null;
                }

                plword = buffer.toString();

                Log.d("plword", "plword");

                return plword;



            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error", e);

                return null;

            } finally {

                if (urlConnection != null) {
                    urlConnection.disconnect();
                }

                if (reader != null) {

                    try {

                        reader.close();

                    } catch (final Exception e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }

                }

            }


            //  return null;
        }




        //    @Override
        //    protected Void OnProgressUpdate(String...params) {

        //        String plword = params[0];

        //        textViewPulledTheWord.setText(params[0]);
        //    }



        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);

            // textViewPulledTheWord.setText(plword);
            textViewPulledTheWord.setText(s);

            //  Log.i("json", s);

        }


        @Override
        protected void onProgressUpdate(String... result) {

            //textViewPulledTheWord.setText(plword[1]);
            textViewPulledTheWord.setText("lkjasdf");
        }


    }




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
                URL url = new URL("http://10.0.2.2:8080/PracticeExercise/rest/json/metallica/post");


                //          URL url = new URL("http://10.0.2.2:8080/PracticeWords/mymethods/post");


                //         URL url = new URL("http://10.0.2.2:8080/PracticeRest/rest/wordservice/postengword"); //here
                //      JSONObject postDataParams = new JSONObject();
                //      postDataParams.put("engword", JsonDATA);
                //      Log.e("params", postDataParams.toString());


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Length","" + String.valueOf(JsonDATA));
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("engword", JsonDATA);
                Log.e("params", postDataParams.toString());


                // OutputStream os = conn.getOutputStream();
                // BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                wr.writeBytes(postDataParams.toString());

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
        protected void onProgressUpdate(String...params) {

            //String engword = params[0];
            String engword = params[2];

            textViewTypedEnglishWord.setText(engWord);

            // textViewTypedEngWord.setText(engword);

        }



        @Override
        //  protected void onPostExecute(String... results) {
        protected void onPostExecute(String result) {




            //super.onPostExecute(result);

            //String engWord = results[1];
            //        String result = results[0];


            textViewTypedEnglishWord.setText(engWord);

            //  textViewTypedEngWord.setText(engWord);

            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();


        }



        public String getPostDataString(JSONObject  params) throws JSONException, UnsupportedEncodingException {

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