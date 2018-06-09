package com.ick.evento;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private Button buttonValid;
    private EditText editTextId;
    private EditText editTextPassword;
    private ImageView mIvLogo;
    private Handler mHandlerTada;
    private int mShortDelay;

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mDrawer=(DrawerLayout) findViewById(R.id.drawerLayout);
        mToogle=new ActionBarDrawerToggle(this,mDrawer, R.string.app_name, R.string.category_Login);
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
