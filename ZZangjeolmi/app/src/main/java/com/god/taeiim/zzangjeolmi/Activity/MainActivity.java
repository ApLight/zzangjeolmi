package com.god.taeiim.zzangjeolmi.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.god.taeiim.zzangjeolmi.R;
import com.god.taeiim.zzangjeolmi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

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
    }


}
