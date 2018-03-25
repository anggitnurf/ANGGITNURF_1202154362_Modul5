package com.anggit.android.anggitnurf_1202154362_modul5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    //declare variable
    TextView shapeclr;
    int colorid;
    AlertDialog.Builder alert;
    SharedPreferences.Editor shape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Pengaturan");

        //making alert dialog
        alert = new AlertDialog.Builder(this);

        //initiate shared preference
        SharedPreferences sharedP = getApplicationContext().getSharedPreferences("Preferences", 0);
        shape = sharedP.edit();
        colorid = sharedP.getInt("Colourground", R.color.white);
        //access textview from layout
        shapeclr = findViewById(R.id.shapecolor);
        //set shape color with color id choosed
        shapeclr.setText(getShapeColor(colorid));
    }

    //if back button clicked
    @Override
    public void onBackPressed() {
        //intent from setting To list to do
        Intent intent = new Intent(Settings.this, List.class);
        //start intent
        startActivity(intent);
        //closing intent activity
        finish();
    }

    //method when menu choosed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            // start onbackpressed method
            this.onBackPressed();
        }
        return true;
    }

    //get sring color to change shape color
    public String getShapeColor(int i){
        if (i==R.color.red){
            return "Red";
        }else if (i==R.color.green){
            return "Green";
        }else if (i==R.color.blue){
            return "Blue";
        }else{
            return "Default";
        }
    }

    //get id from color that'll be used
    public int getColorid(int i){
        if (i==R.color.red){
            return R.id.red;
        }else if (i==R.color.green){
            return R.id.green;
        }else if (i==R.color.blue){
            return R.id.blue;
        }else{
            return R.id.white;
        }
    }

    public void choosecolor(View view) {
        //set dialog alert title
        alert.setTitle("Shape Color");
        //make new view
        View view1 = getLayoutInflater().inflate(R.layout.colorset, null);
        //show view that has been made
        alert.setView(view1);

        //access radio group in layout
        final RadioGroup radG = view1.findViewById(R.id.radiocolor);
        radG.check(getColorid(colorid));

        //when Ok clicked
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //mendapatkan id radio button yang di pilih
                int a = radG.getCheckedRadioButtonId();
                switch (a){
                    case R.id.red:
                        colorid = R.color.red;
                        break;
                    case R.id.green:
                        colorid = R.color.green;
                        break;
                    case R.id.blue:
                        colorid = R.color.blue;
                        break;
                    case R.id.white:
                        colorid = R.color.white;
                        break;
                }
                //set shap color to color you choose
                shapeclr.setText(getShapeColor(colorid));
                //put shared preference
                shape.putInt("Colourground", colorid);
                //commit shared preference
                shape.commit();
            }
        });

        //when cancel button clicked
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //make and show alert dialog
        alert.create().show();
    }
}
