package com.example.pattepepatta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class twoplayer extends AppCompatActivity {

    EditText p1name,p2name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twoplayer);

        p1name = (EditText) findViewById(R.id.tp_n1);
        p2name = (EditText) findViewById(R.id.tp_n2);
    }

    public void twogame(View v) {
        Intent i = new Intent(this, Tgame.class);
        i.putExtra("box1",p1name.getText().toString());
        i.putExtra("box2",p2name.getText().toString());
        startActivity(i);
    }

}