package com.alluciam.asugururibbons;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class ChooseUniformProfile extends AppCompatActivity {

    private String fileNameToUse = "profile1.txt";
    private String TAG_WRITE_READ_FILE = "TAG_WRITE_READ_FILE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_uniform_profile);
    }


    // ===== HERE STARTS THE openProfile1 ====================
    public void openProfile1(View view) {

        Context ctx = getApplicationContext();
        Toast.makeText(ctx, "Button was pressed.", Toast.LENGTH_SHORT).show();
//=============  trying to set folder as phone/dcim/alluciam/
        final File path =
                Environment.getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_DCIM + "/Alluciam/");

//        File file = new File(getApplicationContext().getFilesDir(),fileNameToUse);
        File file = new File(path, fileNameToUse);


        if(file.exists()){
            //Do something
            Toast.makeText(ctx, "File was found.", Toast.LENGTH_SHORT).show();
            Intent intent;
            intent = new Intent(this, UniformDrawer.class);
            startActivity(intent);
        }
        else{
            //Nothing
            Toast.makeText(ctx, "File NOT found!.", Toast.LENGTH_SHORT).show();
        }
    }


}
