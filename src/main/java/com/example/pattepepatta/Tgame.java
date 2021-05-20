package com.example.pattepepatta;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Arrays;
import java.util.Collections;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Tgame extends AppCompatActivity {

    TextView name1, name2, p1rem, p1col, p2rem, p2col;
    ArrayList<String> deckofcards = new ArrayList<String>(Arrays.asList("c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "ct", "ca", "ck", "cq", "cj", "d2", "d3", "d4", "d5", "d6", "d7",
            "d8", "d9", "dt", "da", "dk", "dq", "dj", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "st", "sa", "sq", "sk", "sj", "h2", "h3",
            "h4", "h5", "h6", "h7", "h8", "h9", "ht", "ha", "hk", "hq", "hj"));
    ArrayList<String> player1 = new ArrayList<String>();
    ArrayList<String> player2 = new ArrayList<String>();
    ArrayList<String> throwncards = new ArrayList<String>();
    ImageView iv;
    String plyr1,plyr2;
    int  p1collected = 0, p2collected = 0;
    Button p1,p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tgame);

        name1 = (TextView) findViewById(R.id.player1tp);
        name2 = (TextView) findViewById(R.id.player2tp);
        p1rem = (TextView) findViewById(R.id.p1rem);
        p2rem = (TextView) findViewById(R.id.p2rem);
        p2col = (TextView) findViewById(R.id.p2col);
        p1col = (TextView) findViewById(R.id.p1col);
        iv = (ImageView) findViewById(R.id.deck);
        p1 = (Button) findViewById(R.id.p1btn);
        p2 = (Button) findViewById(R.id.p2btn);

        Intent i = getIntent();
        String text1 = i.getStringExtra("box1");
        plyr1 = text1;
        String text2 = i.getStringExtra("box2");
        plyr2=text2;
        name1.setText(text1);
        name2.setText(text2);
        Collections.shuffle(deckofcards);
        for (int j = 0; j < 26; j++) {
            player1.add(deckofcards.get(j));
            player2.add(deckofcards.get(j + 26));
        }
        Collections.shuffle(player1);
        Collections.shuffle(player2);

        Toast.makeText(getApplicationContext(), "Cards Dealt!", Toast.LENGTH_LONG).show();

    }

    public void makemove1(View v) {
        String s1 = player1.remove(0);
        String uri = "@drawable/" + s1;  // where myresource (without the extension) is the file
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        iv.setImageDrawable(res);
        p1rem.setText(Integer.toString(player1.size()));
        int i = throwncards.size();
        throwncards.add(s1);
        if (i != 0) {
            if (s1.charAt(1) == throwncards.get(i - 1).charAt(1)) {
                p1collected += i + 1;
                throwncards.clear();
                p1col.setText(Integer.toString(p1collected));
                String str1 = plyr1+" got "+p1collected+" cards.";
                Toast.makeText(getApplicationContext(), str1, Toast.LENGTH_LONG).show();
            }
        }
        p1.setVisibility(View.INVISIBLE);
        p2.setVisibility(View.VISIBLE);
        gameover();
    }

    public void makemove2(View v) {
        String s2 = player2.remove(0);
        String uri = "@drawable/" + s2;  // where myresource (without the extension) is the file
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        iv.setImageDrawable(res);
        p2rem.setText(Integer.toString(player2.size()));
        int i = throwncards.size();
        throwncards.add(s2);
        if (i != 0) {
            if (s2.charAt(1) == throwncards.get(i - 1).charAt(1)) {
                p2collected += i + 1;
                throwncards.clear();
                p2col.setText(Integer.toString(p2collected));
                String str2 = plyr2+" got "+p2collected+" cards.";
                Toast.makeText(getApplicationContext(), str2, Toast.LENGTH_LONG).show();
            }
        }
        p2.setVisibility(View.INVISIBLE);
        p1.setVisibility(View.VISIBLE);
        gameover();
    }

    public void gameover() {
        if(player1.size()==0 && player2.size()==0) {
            String str;
            if(p2collected==p1collected)
                str = "GAME TIED!!!";
            else if(p2collected>p1collected)
                str = "GAME OVER!! "+plyr2+" won the game with "+p2collected+" cards";
            else
                str = "GAME OVER!! "+plyr1+" won the game with "+p1collected+" cards";
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}