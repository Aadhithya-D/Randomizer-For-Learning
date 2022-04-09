package com.example.randomizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static android.text.Html.fromHtml;

public class MainActivity extends AppCompatActivity {
    Button b_read;
    TextView tv_view;
    List<String> list = new ArrayList<String>();
    String[] outputList = new ArrayList<String>().toArray(new String[0]);
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView link = (TextView) findViewById(R.id.textView);
        b_read = (Button) findViewById(R.id.button);
        tv_view = (TextView) findViewById(R.id.textView2);
        final String[] text = {""};

        try {
            InputStream is = getAssets().open("Countries.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String completeString = new String(buffer);
            list = Arrays.asList(completeString.split(","));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        b_read.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          int upperBound = list.size();
                                          int randomIndex = rand.nextInt(upperBound);
                                          text[0] = list.get(randomIndex);
                                          List<String> tempList = new ArrayList<String>();
                                          tempList = Arrays.asList(text[0].split(" "));
                                          int len = tempList.size();
                                          if (len == 1) {
                                              String wordLink = "https:/en.wikipedia.org/wiki/" + text[0];
                                              String linkText = " <a href=" + wordLink + ">View in wikipedia</a>";
                                              link.setText(fromHtml(linkText));
                                              link.setMovementMethod(LinkMovementMethod.getInstance());
                                              tv_view.setText(text[0]);
                                          } else {
                                              int i = 0;
                                              String finalText = "";
                                              while (i < len) {
                                                  finalText = finalText + tempList.get(i) + "_";
                                                  i++;
                                              }
                                              String wordLink = "https:/en.wikipedia.org/wiki/" + finalText;
                                              String linkText = " <a href=" + wordLink + ">View in wikipedia</a>";
                                              link.setText(fromHtml(linkText));
                                              link.setMovementMethod(LinkMovementMethod.getInstance());
                                              tv_view.setText(text[0]);
                                          }
                                      }

                                  }
        );
    }
}