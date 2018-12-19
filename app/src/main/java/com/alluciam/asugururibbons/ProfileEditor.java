package com.alluciam.asugururibbons;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class ProfileEditor extends AppCompatActivity {
    //   I am declaring some public variables in here to get access to them in all methods in this activity.
//   I don't know if this is necessary but I thought so, and it is working, so hey! LoL.
//  First, this is totalAmountOfRibbons, add to this when you put more ribbons available in the app...
    public int totalAmountOfRibbons = 12;
    //   Secondly, I am declaring an array variable that will hold the state of checkboxes for every ribbon.
// I have put a +1 to the totalAmountOfRibbons, because otherwise this creates an array starting from [0] and ending to one less than totalAmountOfRibbons.
    public int[] ribbonCheckVariable = new int[totalAmountOfRibbons+1];
    private String fileNameToUse = "profile1.txt";

    String ribbonstr = "";
    // ===========permission for storage things again
    private static final int PERMISSION_REQUEST_CODE = 1;
 //   private String TAG_WRITE_READ_FILE = "TAG_WRITE_READ_FILE";
  //==========================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor);
        //==== trying some permission code==================================================
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
        //========================================================================



    }


 // ==========   HERE STARTS THE save file class for the BUTTON "buttonSaveProfile" ========================

public void saveProfile(View view) {
  // first let's get a string made from the ribbonCheckVariable array...
    ribbonstr = "Ribbons that are selected in the profile1:"+"\n";
        for (int looppi=1; looppi<ribbonCheckVariable.length; looppi++){
            ribbonstr = ribbonstr + Integer.toString(ribbonCheckVariable[looppi]);
        }//looppi ends here in this bracket
    ribbonstr = ribbonstr + "\n";
// next we must create a file, put the ribbon string in there, then close the file.
    if(TextUtils.isEmpty(ribbonstr))
    {
        Toast.makeText(getApplicationContext(), "The ribbonstr string is empty! Failed to save.", Toast.LENGTH_LONG).show();
        return;
    }else {
// ====== writing file to external storage ===============================
        //=================== Get the directory for the user's public pictures directory.
        final File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM + "/Alluciam/");
        // Make sure the path directory exists.
        if(!path.exists())
        {
            // Make it, if it doesn't exist
            path.mkdirs();
        }

        // Here starts the writing of a file
        // check if we have permission to write to external storage
        if (isExternalStorageWritable()){
        // open the file location in somekind of holder variable
         File textFile = new File(path, fileNameToUse);
        textFile.setReadable(true, false);
        // check for exceptions (errors while writing the file) and catch them if they happen
        try {
            FileOutputStream fos = new FileOutputStream(textFile);
        // next line writes the ribbonstr into the file. It is one string of 0 and 1 for the state of every ribbon
            fos.write(ribbonstr.getBytes());
            // close the fileoutputstream
        fos.close();
             Toast.makeText(this, "File Saved.", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
             Toast.makeText(this, "IOException.", Toast.LENGTH_SHORT).show();
             e.printStackTrace();
         }
           //  Here ends the writing of a file
        }// here ends if isExternalStorageWritable
        else{
                  Toast.makeText(this, "Cannot write to External Storage!.", Toast.LENGTH_SHORT).show();
            }
    } // ends the else for if textUtils.isEmpty


    } // this bracket ends the public void saveProfile(View view)







    //  ===================== HERE STARTS THE LONG LIST OF CHECKBOX CHECKINGS ==================
    // ================== I HAVE NOT FIGURED ANY BETTER WAY TO DO THIS YET. ==============
    public void onCheckboxClicked(View view) {


        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        // When you add more ribbons and checkboxes, just copy paste more of these and change those two running numbers in the array variable ribbonCheckVariable
        // and the ribbonCheckBoxNNN must point to the same checkbox with that name in the activity_profile_editor.xml layout file.
        switch (view.getId()) {
            case R.id.ribbonCheckBox001:
                if (checked)
                    ribbonCheckVariable[1] = 1;
                else ribbonCheckVariable[1] = 0;
                break;
            case R.id.ribbonCheckBox002:
                if (checked)
                    ribbonCheckVariable[2] = 1;
                else ribbonCheckVariable[2] = 0;
                break;
            case R.id.ribbonCheckBox003:
                if (checked)
                    ribbonCheckVariable[3] = 1;
                else ribbonCheckVariable[3] = 0;
                break;
            case R.id.ribbonCheckBox004:
                if (checked)
                    ribbonCheckVariable[4] = 1;
                else ribbonCheckVariable[4] = 0;
                break;
            case R.id.ribbonCheckBox005:
                if (checked)
                    ribbonCheckVariable[5] = 1;
                else ribbonCheckVariable[5] = 0;
                break;
            case R.id.ribbonCheckBox006:
                if (checked)
                    ribbonCheckVariable[6] = 1;
                else ribbonCheckVariable[6] = 0;
                break;
            case R.id.ribbonCheckBox007:
                if (checked)
                    ribbonCheckVariable[7] = 1;
                else ribbonCheckVariable[7] = 0;
                break;
            case R.id.ribbonCheckBox008:
                if (checked)
                    ribbonCheckVariable[8] = 1;
                else ribbonCheckVariable[8] = 0;
                break;
            case R.id.ribbonCheckBox009:
                if (checked)
                    ribbonCheckVariable[9] = 1;
                else ribbonCheckVariable[9] = 0;
                break;
            case R.id.ribbonCheckBox010:
                if (checked)
                    ribbonCheckVariable[10] = 1;
                else ribbonCheckVariable[10] = 0;
                break;
            case R.id.ribbonCheckBox011:
                if (checked)
                    ribbonCheckVariable[11] = 1;
                else ribbonCheckVariable[11] = 0;
                break;
            case R.id.ribbonCheckBox012:
                if (checked)
                    ribbonCheckVariable[12] = 1;
                else ribbonCheckVariable[12] = 0;
                break;
        }

    } // =========== this bracket ends the checkbox checklist onCheckboxClicked ====================
    // =========================================================================================================









    //==========================================================================================================
    //============= from here on below is only code for permissions for external storage================
    //========================================================================================================
// =============== Check if SDCard (external storage can be written into ==============
    private boolean isExternalStorageWritable(){
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Log.i("State", "Yes, it is writable!");
            return true;
        } else{
            return false;
        }
    }// here ends isExternalStorageWritable


    //============ trying out some permission code========
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(ProfileEditor.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(ProfileEditor.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(ProfileEditor.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(ProfileEditor.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

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


    //=======================




}
