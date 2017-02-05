package com.ase.aseapp;

import android.app.Activity;

/**
 * Created by alex on 19.01.17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MenuActivity extends Activity {

    private Person person;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        person = (Person) intent.getExtras().get("PERSON");

        Button qrCode = (Button) findViewById(R.id.buttonQr);
        qrCode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), QRAttendanceActivity.class);
                myIntent.putExtra("PERSON", person);
                startActivity(myIntent);
            }

        });

        Button bonusStatus = (Button) findViewById(R.id.buttonBonus);
        bonusStatus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), QRPresentationActivity.class);
                myIntent.putExtra("PERSON", person);
                startActivity(myIntent);
            }

        });

    }

    public void switchWindow(View view){

    }
}

