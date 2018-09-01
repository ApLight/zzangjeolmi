package com.god.taeiim.zzangjeolmi.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.god.taeiim.zzangjeolmi.Model.UserInfo;
import com.god.taeiim.zzangjeolmi.R;
import com.god.taeiim.zzangjeolmi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    UserInfo userInfo = new UserInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setActivity(this);

        final Animation shakeAnim = AnimationUtils.loadAnimation(this,R.anim.anim_shake);
        shakeAnim.setInterpolator(this,R.anim.interpolator_bounce);

        binding.icPolarbear.setOnClickListener(v-> {
            binding.icPolarbear.startAnimation(shakeAnim);
        });

        binding.btnIntentGame.setOnClickListener(v->startActivity(new Intent(MainActivity.this, GamesActivity.class)));
        binding.layoutLevel.setOnClickListener(v-> startActivity(new Intent(MainActivity.this, LevelShowActivity.class)));

        setUpLevelData(40,3);
        setUpLevelProgressBar();
    }

    private void setUpLevelProgressBar() {
        binding.levelProgressBar.setMax(userInfo.getCurrentLevelMaxExp());
        binding.levelProgressBar.setProgress(userInfo.getCurrentExp());
        binding.currentExpTv.setText(String.valueOf(userInfo.getCurrentExp()));
        binding.maxExpTv.setText(String.valueOf("/ "+userInfo.getCurrentLevelMaxExp()));

        binding.levelTv.setText("Lv."+userInfo.getCurrentLevel());
    }

    private void setUpLevelData(int exp, int level) {
        userInfo.setCurrentExp(exp);
        userInfo.setCurrentLevel(level);
    }

}
