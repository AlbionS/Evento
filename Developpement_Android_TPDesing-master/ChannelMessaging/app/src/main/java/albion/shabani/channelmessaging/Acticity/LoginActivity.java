package albion.shabani.channelmessaging.Acticity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.design.widget.Snackbar;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.androidanimations.library.attention.TadaAnimator;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;


import albion.shabani.channelmessaging.R;
import albion.shabani.channelmessaging.Clases.Connexion;
import albion.shabani.channelmessaging.Request.HttpPostHandler;
import albion.shabani.channelmessaging.Request.OnDownloadListener;
import albion.shabani.channelmessaging.Request.PostRequest;

/**
 * Created by shabania on 26/02/2018.
 */
public class LoginActivity extends Activity implements View.OnClickListener,OnDownloadListener {

    private Button buttonValid;
    private EditText editTextId;
    private EditText editTextPassword;
    private ImageView mIvLogo;
    private Handler mHandlerTada;
    private int mShortDelay;
    private AVLoadingIndicatorView avi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonValid = (Button) findViewById(R.id.buttonValid);
        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        mIvLogo= (ImageView) findViewById(R.id.mIvLogo);
        avi =(AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.hide();
        buttonValid.setOnClickListener(this);

        mHandlerTada = new Handler(); // android.os.handler
        mShortDelay = 4000; //milliseconds

        mHandlerTada.postDelayed(new Runnable(){
            public void run(){
                YoYo.with(Techniques.Tada).playOn(mIvLogo);
                mHandlerTada.postDelayed(this, mShortDelay);
            }
        }, mShortDelay);

    }
    @Override
    public void onClick(View v) {
        avi.show();

        Animation animSlideLeft = AnimationUtils.loadAnimation(this, R.anim.slide_left);
        v.startAnimation(animSlideLeft);

        HttpPostHandler post = new HttpPostHandler();
        post.addOnDownloadListener(this);
        HashMap<String,String> hm = new HashMap<String,String>();
        hm.put("username",editTextId.getText().toString());
        hm.put("password",editTextPassword.getText().toString());

        post.setHttpPostHandler(getApplicationContext());
        post.execute(new PostRequest("http://www.raphaelbischof.fr/messaging/?function=connect",hm));
        //v.clearAnimation();
    }


    @Override
    public void onDownloadComplete(String downloadedContent) {
        avi.hide();
        Toast.makeText(getApplicationContext(),"Connected",Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        Connexion conn = gson.fromJson(downloadedContent, Connexion.class);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("AccessToken", conn.getAccesstoken());
        editor.commit();

        Intent myIntent = new Intent(getApplicationContext(),ChannelListActivity.class);
       // startActivity(myIntent);

       // Intent loginIntent = new Intent(LoginActivity.this, ChannelListActivity.class);
        startActivity(myIntent, ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, mIvLogo, "logo").toBundle());


    }

    @Override
    public void onDownloadError(String error) {
        Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
        /*
        Snackbar.make(this, "Your message here",
                        Snackbar.LENGTH_SHORT)
                .show();
                */
    }
}