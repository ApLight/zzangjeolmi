package com.god.taeiim.zzangjeolmi.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bumptech.glide.Glide;
import com.god.taeiim.zzangjeolmi.APIUrl;
import com.god.taeiim.zzangjeolmi.Model.UserInfo;
import com.god.taeiim.zzangjeolmi.R;
import com.god.taeiim.zzangjeolmi.RestAPI;
import com.god.taeiim.zzangjeolmi.databinding.ActivityMainBinding;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    UserInfo userInfo = new UserInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setActivity(this);

        getMyInfo();

        Intent getIntent = getIntent();
        setUpLevelData(getIntent.getIntExtra("exp",0), getIntent.getIntExtra("level",1));

        final Animation shakeAnim = AnimationUtils.loadAnimation(this,R.anim.anim_shake);
        shakeAnim.setInterpolator(this,R.anim.interpolator_bounce);

        Glide.with(this).load(R.drawable.seolgi).into(binding.gifSeolgi);

        binding.btnIntentGame.setOnClickListener(v->startActivity(new Intent(MainActivity.this, GamesActivity.class)));
        binding.layoutLevel.setOnClickListener(v-> startActivity(new Intent(MainActivity.this, LevelShowActivity.class)));

        setUpLevelProgressBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMyInfo();
    }

    private void setUpLevelProgressBar() {
        binding.levelProgressBar.setMax(100);
        binding.levelProgressBar.setProgress(userInfo.getCurrentExp());
        binding.currentExpTv.setText(String.valueOf(userInfo.getCurrentExp()));

        setUpLevelTv(userInfo.getCurrentLevel());
    }

    private void setUpLevelTv(int level){
        binding.levelTv.setText("Lv."+level);
    }

    private void setUpLevelData(int exp, int level) {
        userInfo.setCurrentLevel(level);
        setUpLevelTv(level);
        userInfo.setCurrentExp(exp);
        setUpLevelProgressBar();
    }

    private void getMyInfo(){
        Retrofit builder = new Retrofit.Builder()
                .baseUrl(APIUrl.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestAPI restAPI = builder.create(RestAPI.class);

        SharedPreferences pref = getSharedPreferences("userID", MODE_PRIVATE);

        Call<JsonObject> call = restAPI.updateExp(0,pref.getLong("userID", 0));
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.code() == 200) {
                    Log.d("response----", response.body().toString());
                    JsonObject firstObject = response.body().getAsJsonObject();
                    JsonObject accountObject = firstObject.getAsJsonObject("account");

                    int level = accountObject.getAsJsonPrimitive("level").getAsInt();
                    int exp = accountObject.getAsJsonPrimitive("exp").getAsInt();

                    setUpLevelData(exp,level);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("ERROR! onFailure!", t.toString());
            }
        });
    }

}
