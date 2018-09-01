package com.god.taeiim.zzangjeolmi.Activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.god.taeiim.zzangjeolmi.R;
import com.god.taeiim.zzangjeolmi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setActivity(this);

        final Animation shakeAnim = AnimationUtils.loadAnimation(this,R.anim.shake);
        shakeAnim.setInterpolator(this,R.anim.bounce_interpolator);

        binding.icPolarbear.setOnClickListener(v-> {
            binding.icPolarbear.startAnimation(shakeAnim);
        });
    }


}
