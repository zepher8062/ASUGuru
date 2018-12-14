package com.alluciam.asugururibbons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    /** Called when the user taps the ribbon builder button */
    public void openRackBuilder(View view) {
        Intent intent;
        intent = new Intent(this, RibbonActivity.class);
       // nämä ylimääräiset tekstit ovat jäänteitä Android Studion tutoriaalista, jossa käyttäjän antama teksti siirrettiin button-painalluksen mukana toiseen activityyn intentillä
        //      EditText editText = (EditText) findViewById(R.id.editText);
        //     String message = editText.getText().toString()+firstRibbon;
        //     intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }
}
