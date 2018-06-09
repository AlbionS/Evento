package com.ick.evento;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Music extends AppCompatActivity {


    TextView tvConcerts;
    TextView tvLocations;
    TextView tvArtists;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToogle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);


        mDrawer=(DrawerLayout) findViewById(R.id.drawerLayout);
        mToogle=new ActionBarDrawerToggle(this,mDrawer, R.string.app_name, R.string.category_business);
        mDrawer.addDrawerListener(mToogle);
        mToogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvArtists=(TextView) findViewById(R.id.tvArtists);
        tvConcerts=(TextView) findViewById(R.id.tvConcerts);
        tvLocations=(TextView) findViewById(R.id.tvLocations);


        tvConcerts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            tvConcerts.setBackgroundResource(R.drawable.border);
                tvLocations.setBackgroundResource(R.drawable.reset);
                tvArtists.setBackgroundResource(R.drawable.reset);
            }
        });


        tvArtists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvArtists.setBackgroundResource(R.drawable.border);
                tvConcerts.setBackgroundResource(R.drawable.reset);
                tvLocations.setBackgroundResource(R.drawable.reset);

            }
        });
        tvLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvLocations.setBackgroundResource(R.drawable.border);
                tvConcerts.setBackgroundResource(R.drawable.reset);
                tvArtists.setBackgroundResource(R.drawable.reset);

            }
        });
    }
    //Back  button and menu button
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
