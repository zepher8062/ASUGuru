package com.alluciam.asugururibbons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


    }

    /** Called when the user taps the ribbon builder button */
    public void openUniformChooser(View view) {
        Intent intent;
        intent = new Intent(this, ChooseUniformProfile.class);
        startActivity(intent);

    }

    /** Called when the user taps the Profile editor button */
    public void openProfileEditor(View view) {
        Intent intent;
        intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);

    }
}
