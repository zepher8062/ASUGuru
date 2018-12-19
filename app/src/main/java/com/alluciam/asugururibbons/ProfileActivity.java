package com.alluciam.asugururibbons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    /** Called when the user taps the ribbon builder button */
    public void openProfileEditor(View view) {
        Intent intent;
        intent = new Intent(this, ProfileEditor.class);
        startActivity(intent);

    }

}
