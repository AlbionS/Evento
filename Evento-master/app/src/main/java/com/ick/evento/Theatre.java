package com.ick.evento;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class Theatre extends AppCompatActivity {


    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theatre);

        mDrawer=(DrawerLayout) findViewById(R.id.drawerLayout);
        mToogle=new ActionBarDrawerToggle(this,mDrawer, R.string.app_name, R.string.category_business);
        mDrawer.addDrawerListener(mToogle);
        mToogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
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
