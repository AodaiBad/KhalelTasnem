package com.tasnem.khalel.khaleltasnem;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class WelcomeActivity extends AppCompatActivity {
    private static final int SPLASH_DELAY = 3000;
    private final Handler mHandler = new Handler();
    private final Launcher mLauncher = new Launcher() {
    };

    LinearLayout l1;
    Button btnsub;
    Animation uptodown, downtoup;

    protected void onStart() {
        super.onStart();

        mHandler.postDelayed(mLauncher, SPLASH_DELAY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btnsub = (Button) findViewById(R.id.buttonsub);
        l1 = (LinearLayout) findViewById(R.id.l1);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        l1.setAnimation(uptodown);
    }

    @Override
    protected void onStop() {
        mHandler.removeCallbacks(mLauncher);
        super.onStop();
    }

    private void launch() {
        if (!isFinishing()) {
            Intent i = new Intent(this,LogInAct.class);
            startActivity(i);
            finish();
        }
    }

    private class Launcher implements Runnable {
        @Override
        public void run() {
            launch();

        }
    }
}
