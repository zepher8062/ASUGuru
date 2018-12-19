package com.alluciam.asugururibbons;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;



public class UniformDrawer extends AppCompatActivity {

     String fileNameToUse = "profile1.txt";
    String ribbonstr = "empty";

    // ===========permission for storage things again
    private static final int PERMISSION_REQUEST_CODE = 1;

    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uniform_drawer);
        txt = (TextView) findViewById(R.id.txt);
        //==== Check if app has permission to read/write external storage and request permission if it is not yet set. ================================
        // because new android phones ask for permission only when the app needs and requests them, not like it used to ask for every permission during installation of app.
        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkPermission())
            {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code
            } else {
                requestPermission(); // Code for permission
            }
        }
        else
        {
            // Code for Below 23 API Oriented Device
            // Do next code
        }
        //============== Checking for permissions during the onCreate ends here. ==========================
        txt.setText("Setting text into the textview to see what happens in case of error");
    // next line runs the method that opens our profile file and reads it.
       readFile();
        // prepare the image files into the hidden folders that can not be read outside the app
       //...............
        // put the code in here to continue to the uniform zooming activities after this activity has prepared the images.
       //...................

    }  // this ends the onCreate

    //========================= here starts the readfile method. Chancge it to something that reads XML files. ======================
    private void readFile() {
if(isExternalStorageWritable()){
    Toast.makeText(this, "Reading file.", Toast.LENGTH_SHORT).show();
    StringBuilder sb = new StringBuilder();
    try{
        File textFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM + "/Alluciam/"), fileNameToUse);
    FileInputStream fis = new FileInputStream(textFile);
        if(fis != null){
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader buff = new BufferedReader(isr);
            String line = null;
            while((line = buff.readLine()) != null){
                sb.append(line + "\n\n");
            }// this ends while line=buff.readLine
            fis.close();
        } // this ends if fis != null
        Toast.makeText(this, "File has been read and closed.", Toast.LENGTH_LONG).show();
        txt.setText(sb);
    } // this ends try
    catch (IOException e){
        Toast.makeText(this, "IOException. File not found", Toast.LENGTH_SHORT).show();
        e.printStackTrace();
    }// this ends catch
} // this ends if isExternalStorageWritable
        else{
    Toast.makeText(this, "Cannot read from External Storage.", Toast.LENGTH_SHORT).show();
            }
   }  // this ends readFile


// ====================================================================================================================
//            From here on after is only code related to the asking of external storage permission.
//==============================================================================================================
// =============== Check if SDCard (external storage can be written into ==============
    private boolean isExternalStorageWritable(){
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Log.i("State", "Yes, it is writable!");
            return true;
        } else{
            return false;
        }
    }// here ends isExternalStorageWritable
    // ========= check for permission for external storage =========================
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(UniformDrawer.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

// ========================== requesting for permission =================================================================================
    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(UniformDrawer.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(UniformDrawer.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(UniformDrawer.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }
// ================================ checking what was the result of permission request from the user ===========================
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }
//======================= PERMISSION CHECKING AND REQUESTINGS ENDS HERE =====================================



}