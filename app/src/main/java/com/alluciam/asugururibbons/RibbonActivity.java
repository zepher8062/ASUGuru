package com.alluciam.asugururibbons;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.CheckBox;


public class RibbonActivity extends AppCompatActivity {
//   I am declaring some public variables in here to get access to them in all methods in this activity.
//   I don't know if this is necessary but I thought so, and it is working, so hey! LoL.
//  First, this is totalAmountOfRibbons, add to this when you put more ribbons available in the app...
    public int totalAmountOfRibbons = 12;
//   Secondly, I am declaring an array variable that will hold the state of checkboxes for every ribbon.
// I have put a +1 to the totalAmountOfRibbons, because otherwise this creates an array starting from [0] and ending to one less than totalAmountOfRibbons.
    public int[] ribbonCheckVariable = new int[totalAmountOfRibbons+1];

// onCreate will be activated when this page is opened in the app and it instruct to load the layout of the screen from activity_ribbon.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ribbon);
    }


    // ========================= HERE STARTS THE METHOD THAT IS BROUGHT TO ACTION ==================
    // ========================= BY TAPPING THE "Show Rack Layout" BUTTON ==================
    //========================== You can see that there is an onClick pointing to this method in the activity_ribbon.xml layout file.===============
    public void buildRack(View view) {

//   Important! setContentView MUST BE HERE ALSO; Otherwise this method can not access the items on screen setup in the layout file activity_ribbon.mxl!
//                                                and thus preventing the ribbon rack to be visible in the rackDrawSurface described in the said .xml file.
//          10.December.2018 It took me at least an hour to figure what was wrong. Then the solution just popped into my mind..
        setContentView(R.layout.activity_ribbon);
// Now that we can access the activity_ribbon.xml, we can start pointing into the objects it has...
// So let's get a holder for that surface area and create a canvas in there so we can start using the BitmapFactory commands
        SurfaceView rackDrawSurface = (SurfaceView) findViewById(R.id.rackDrawSurface);
        rackDrawSurface.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas canvas = holder.lockCanvas();
//        a fancy color for the backgound of the rack, should be something like the uniform jacket color.
                canvas.drawColor(0XFF556254);

// the next lines will set BitmapFactory options to load the images of ribbons in pixel per pixel their original size without scaling to dpi.
// AND THAT WILL ENABLE US TO STAMP THEM PRECISELY SPOT ON, WHERE WE WANT THEM ON THE CANVAS! HOORAY!
                BitmapFactory.Options noscaleoption = new BitmapFactory.Options();
                noscaleoption.inScaled = false;
// Let's create an array of bitmaps, I will call it "kuvat[]" (it is a Finnish word, meaning pictures[] in english)
//  The array enables us to have a loop that can access the correct image from the array by using the number which the loop is processing at that moment.
// So if we want to print the image of ribbon number 5 when the loop is processing that ribbon number, it will pull the bitmap from kuvat[5]
// I have put a +1 to the totalAmountOfRibbons, because otherwise this creates an array starting from [0] and ending to one less than totalAmountOfRibbons.
                Bitmap[] kuvat = new Bitmap[totalAmountOfRibbons+1];
// ============= Let's load the images from the res folder into the memory as bitmaps ====================
                              kuvat[1] = BitmapFactory.decodeResource(getResources(), R.drawable.medal_of_honor_ribbon, noscaleoption);
                              kuvat[2] = BitmapFactory.decodeResource(getResources(), R.drawable.army_distinguished_service_cross_ribbon, noscaleoption);
                              kuvat[3] = BitmapFactory.decodeResource(getResources(), R.drawable.defense_distinguished_service_ribbon, noscaleoption);
                              kuvat[4] = BitmapFactory.decodeResource(getResources(), R.drawable.army_distinguished_service_ribbon, noscaleoption);
                              kuvat[5] = BitmapFactory.decodeResource(getResources(), R.drawable.silver_star_ribbon, noscaleoption);
                              kuvat[6] = BitmapFactory.decodeResource(getResources(), R.drawable.defense_superior_service_ribbon, noscaleoption);
                              kuvat[7] = BitmapFactory.decodeResource(getResources(), R.drawable.legion_of_merit_ribbon, noscaleoption);
                              kuvat[8] = BitmapFactory.decodeResource(getResources(), R.drawable.distinguished_flying_cross_ribbon, noscaleoption);
                              kuvat[9] = BitmapFactory.decodeResource(getResources(), R.drawable.soldier_medal_ribbon, noscaleoption);
                              kuvat[10] = BitmapFactory.decodeResource(getResources(), R.drawable.bronze_star_ribbon, noscaleoption);
                              kuvat[11] = BitmapFactory.decodeResource(getResources(), R.drawable.purple_heart_ribbon, noscaleoption);
                              kuvat[12] = BitmapFactory.decodeResource(getResources(), R.drawable.defense_meritorious_service_ribbon, noscaleoption);
//      You can use this next line to copy more bitmap loading lines, then just adjust the N in kuvat[N] variable and autofill drawable:
//                             kuvat[8] = BitmapFactory.decodeResource(getResources(), R.drawabl, noscaleoption);
//       I suggest having the app/res/values/strings.xml opened next to this RibbonActivity.java so you can see what are the ribbon names for each number.
//       (NOTE: of course you must first have filled in there the lines for <string name="textribbonXXX">Name of This Ribbon</string>)
//       (That would be the first thing to add when adding new ribbons to the app. Then you can use the auto-fill function of Android Studio in the layout .xml too.
//
//
// The drawing of the images to the screen is going to need some location coordinates and
// we are also going to keep track of how many rows have been printed
// so let's make some variables to help us in that task.
// first go through the checkboxes to see how many ribbons have been granted to the user (how many checkboxes the user checked)
                int grantedRibbons = 0;
                for (int i = 1; i < totalAmountOfRibbons+1; i++) {
                    if (ribbonCheckVariable[i] == 1) grantedRibbons++;
                }
// then the width and height of the images of the ribbons (in actual pixels)
                int ribbonWidth = 105;
                int ribbonHeight = 30;
 // and then we initialize the location coordinates for the bottom right ribbon's top left corner because that is where we start stamping the images
                int xMarksTheSpot = (2 * ribbonWidth) + 1;
                int yMarksTheSpot = 4 * ribbonHeight;
                int printedRows = 0;
// then we define the amount of rows simply by dividing the amount of granted ribbons by the amount of ribbons in a row
                int amountOfRows = grantedRibbons / 3;
// and a simple calculation to get the modulo gives us the amount of ribbons left over from full rows
                int amountOfRibbonsInTopRow = grantedRibbons % 3;
 // Is there only one incomplete row with less than 3 ribbons to print? If so, center the first row instead of starting from the bottom right corner:
                if (printedRows == amountOfRows) {
                    if (amountOfRibbonsInTopRow > 0)  //We do not want to divide by zero, hence the IF. Dividing by zero always crashes the app.
                        xMarksTheSpot = (2 * ribbonWidth) - (ribbonWidth / amountOfRibbonsInTopRow);
                    printedRows++;
                }
// ============== Let's do a loop from most unimportant ribbon number totalAmountOfRibbons to the most important ribbon numer 1  ============
// =============== because we are building the rack from bottom to top =======================================================
                for (int looppi = totalAmountOfRibbons; looppi > 0; looppi--) {
                    if (ribbonCheckVariable[looppi] == 1) {
                        // this next line is the actual printing of the ribbon onto the canvas:
                        canvas.drawBitmap(kuvat[looppi], xMarksTheSpot+9, yMarksTheSpot, null);
                        // after each printed ribbon we are moving the coordinates for the next printing
                        xMarksTheSpot = xMarksTheSpot - ribbonWidth;
                        if (xMarksTheSpot <= 0) {
                            xMarksTheSpot = 2 * ribbonWidth + 1;
                            yMarksTheSpot = yMarksTheSpot - ribbonHeight;
                            printedRows++;
                            // and then we again make the top row centered, when all full rows have been dealt with.
                            if (printedRows == amountOfRows) {
                                if (amountOfRibbonsInTopRow > 0)
                                    xMarksTheSpot = (2 * ribbonWidth) - (ribbonWidth / amountOfRibbonsInTopRow);
                                printedRows++;
                            }
                        }
                    }
                } // this bracket ends the "looppi" loop.
// ============ let's make all checkboxes unchecked ( that is a zero ) in the array variable (because the boxes seem to get automatically unchecked anyways )=========
                for (int i = 1; i < totalAmountOfRibbons+1; i++) {
                    ribbonCheckVariable[i] = 0;
                }
// === The next line will reveal the rack of ribbons that was built onto the canvas from the ribbon bitmaps we loaded into the memory ==========
                holder.unlockCanvasAndPost(canvas);
            } // this bracket ends the public void surfaceCreated(SurfaceHolder holder)

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }
        });

    } // ============ this bracket ends the buildRack ================================================


    //  ===================== HERE STARTS THE LONG LIST OF CHECKBOX CHECKINGS ==================
    // ================== I HAVE NOT FIGURED ANY BETTER WAY TO DO THIS YET. ==============
    public void onCheckboxClicked(View view) {


        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        // When you add more ribbons and checkboxes, just copy paste more of these and change those two running numbers in the array variable ribbonCheckVariable
        // and the ribbonCheckBoxNNN must point to the same checkbox with that name in the activity_ribbon.xml layout file.
        switch (view.getId()) {
            case R.id.ribbonCheckBox001:
                if (checked)
                    ribbonCheckVariable[1] = 1;
                break;
            case R.id.ribbonCheckBox002:
                if (checked)
                    ribbonCheckVariable[2] = 1;
                break;
            case R.id.ribbonCheckBox003:
                if (checked)
                    ribbonCheckVariable[3] = 1;
                break;
            case R.id.ribbonCheckBox004:
                if (checked)
                    ribbonCheckVariable[4] = 1;
                break;
            case R.id.ribbonCheckBox005:
                if (checked)
                    ribbonCheckVariable[5] = 1;
                break;
            case R.id.ribbonCheckBox006:
                if (checked)
                    ribbonCheckVariable[6] = 1;
                break;
            case R.id.ribbonCheckBox007:
                if (checked)
                    ribbonCheckVariable[7] = 1;
                break;
            case R.id.ribbonCheckBox008:
                if (checked)
                    ribbonCheckVariable[8] = 1;
                break;
            case R.id.ribbonCheckBox009:
                if (checked)
                    ribbonCheckVariable[9] = 1;
                break;
            case R.id.ribbonCheckBox010:
                if (checked)
                    ribbonCheckVariable[10] = 1;
                break;
            case R.id.ribbonCheckBox011:
                if (checked)
                    ribbonCheckVariable[11] = 1;
                break;
            case R.id.ribbonCheckBox012:
                if (checked)
                    ribbonCheckVariable[12] = 1;
                break;
        }

        } // =========== this bracket ends the checkbox checklist onCheckboxClicked ====================


}

