package com.god.taeiim.zzangjeolmi.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.god.taeiim.zzangjeolmi.R;
import com.god.taeiim.zzangjeolmi.databinding.ActivityGamesBinding;
import com.god.taeiim.zzangjeolmi.databinding.ActivityLevelshowBinding;

/**
 * Created by parktaeim on 2018. 9. 2..
 */

public class LevelShowActivity extends AppCompatActivity {
    ActivityLevelshowBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_levelshow);
        binding.setLevelshowActivity(this);

        binding.finishBtn.setOnClickListener(v->finish());
    }
}
