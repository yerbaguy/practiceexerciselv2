package com.bartoszmaliszewski.practiceexerciselv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by bartoszmaliszewski on 23.05.18.
 */

public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Word> words;
    LayoutInflater inflater;


    public CustomAdapter(Context c, ArrayList<Word> words) {

        this.c = c;
        this.words = words;
    }

    @Override
    public int getCount() {

        return words.size();

      //  return 0;
    }

    @Override
    public Object getItem(int position) {

         return words.get(position);

      //  return null;
    }

    @Override
    public long getItemId(int position) {

         return position;

      //  return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

          if (inflater == null) {

              inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          }

          if (convertView == null) {

              convertView = inflater.inflate(R.layout.model, parent, false);
          }

        TextView engwordText = (TextView) convertView.findViewById(R.id.engwordTxt);
          engwordText.setText(words.get(position).getEngWord());

          final int pos = position;

          convertView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Toast.makeText(c,words.get(pos).getEngWord(), Toast.LENGTH_SHORT).show();
              }
          });



          return convertView;

       // return null;
    }
}
