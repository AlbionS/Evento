package com.ick.evento;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.drm.DrmStore;
import android.media.Image;
import android.os.SystemClock;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


import java.util.function.ToLongBiFunction;

public class MainActivity extends AppCompatActivity {

    ImageButton imgBtn_Music;
    ImageButton imgBtn_Sport;
    ImageButton imgBtn_Business;
    ImageButton imgBtn_Theatre;

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToogle;


    private boolean flagChange = false;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imgBtn_Sport =  findViewById(R.id.imgBtn_Sport);
        imgBtn_Music =  findViewById(R.id.imgBtn_Music);
        imgBtn_Business = findViewById(R.id.imgBtn_Business);
        imgBtn_Theatre = findViewById(R.id.imgBtn_Theatre);

        mDrawer=(DrawerLayout) findViewById(R.id.drawerLayout);
        mToogle=new ActionBarDrawerToggle(this,mDrawer, R.string.app_name, R.string.category_business);
        mDrawer.addDrawerListener(mToogle);
        mToogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        imgBtn_Sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Sport();
            }
        });

        imgBtn_Music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Music();
            }
        });

        imgBtn_Theatre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Theatre();
            }
        });
        imgBtn_Business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Business();
            }
        });




    }



    /* Metodat per krijimin e nje Intenti per kalimin ne aktivitetin tjeter */
    public void Sport(){
        Intent intent_Sport=new Intent(this,Sport.class);
        startActivity(intent_Sport);
    }
    public void Music(){
        Intent intent_music=new Intent(this, Music.class);
        startActivity(intent_music);
    }

    public void Theatre(){
        Intent intent_Theatre=new Intent(this, Theatre.class);
        startActivity(intent_Theatre);
    }
    public void Business(){
        Intent intent_Business=new Intent(this, Business.class);
        startActivity(intent_Business);

    }
    /* End metodat per kalimin ne tjeter aktivitet */

    //Back button and menu app
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToogle.onOptionsItemSelected(item)) {
            return true;
        }
        int id=item.getItemId();
        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
