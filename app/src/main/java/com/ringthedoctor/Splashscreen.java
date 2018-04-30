package com.ringthedoctor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class
Splashscreen extends FragmentActivity {
    SharedPreferences mypref;

    public static final String USER_ID_File = "Userid";
    private FirebaseAuth.AuthStateListener authListener;
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    /** Called when the activity is first created. */
    Thread splashTread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashscreen);

        StartAnimations();
    }
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 2000) {
                        sleep(100);
                        waited += 100;
                    }

                    FirebaseAuth auth;
                    //Get Firebase auth instance
                    auth = FirebaseAuth.getInstance();

                    if (auth.getCurrentUser() != null) {
                        startActivity(new Intent(Splashscreen.this, MainPage.class));
                        Splashscreen.this.finish();

                    }
                    else {
                        startActivity(new Intent(Splashscreen.this, MainActivity.class));
                        Splashscreen.this.finish();
                    }



                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Splashscreen.this.finish();
                }

            }
        };
        splashTread.start();

        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        try {

            if (!packages.isEmpty()) {

                    for (ApplicationInfo packageInfo : packages) {

                        Log.i("mytagg",packageInfo.packageName);

                    }
            }

        } catch (Exception e) {
            // Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

}