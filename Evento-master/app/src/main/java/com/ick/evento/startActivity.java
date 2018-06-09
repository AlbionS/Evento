package com.ick.evento;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class startActivity extends AppCompatActivity {

    ImageView img_Perdoruesi;
    ImageView img_Biznesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        img_Biznesi = (ImageView) findViewById(R.id.biznes_ikona);
        img_Perdoruesi = (ImageView) findViewById(R.id.perdoruesi_ikona);

        img_Perdoruesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perdoruesi();
            }
        });


    }

    public void perdoruesi() {
        Intent intent_perdoruesi = new Intent(this, MainActivity.class);
        startActivity(intent_perdoruesi);

    }
      public void biznesi(){
        Intent intent_perdoruesi=new Intent(this,MainActivity.class);
        startActivity(intent_perdoruesi);
         }


}