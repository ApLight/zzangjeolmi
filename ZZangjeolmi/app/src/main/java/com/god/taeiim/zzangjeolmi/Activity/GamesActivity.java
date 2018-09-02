package com.god.taeiim.zzangjeolmi.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.god.taeiim.RecyclerViewClickListener;
import com.god.taeiim.zzangjeolmi.Adapter.GamesRecyclerAdapter;
import com.god.taeiim.zzangjeolmi.Model.GamesItem;
import com.god.taeiim.zzangjeolmi.R;
import com.god.taeiim.zzangjeolmi.databinding.ActivityGamesBinding;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 9. 1..
 */

public class GamesActivity extends AppCompatActivity {
    ActivityGamesBinding binding;
    private ArrayList<GamesItem> gamesItemArrayList = new ArrayList<>();
    private GamesRecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_games);
        binding.setGamesActivity(this);

        binding.icBack.setOnClickListener(v -> finish());

        setUpGamesItem();
        setUpRecyclerView();

        binding.gamelistRecyclerview.addOnItemTouchListener(new RecyclerViewClickListener(getApplicationContext(), binding.gamelistRecyclerview, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(GamesActivity.this, QuizGameActivity.class));
                        break;

                }
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }

    private void setUpGamesItem() {
        String[] titles = {"환경 O X 퀴즈","환경 영상 받기", "쓰레기받기", "리사이클 게임","벽돌튕기기", "버블버블", "나무키우기", "참참참", "가위바위보", "컵 인증"};
        int[] imgs = {R.drawable.ic_ox, R.drawable.ic_movie, R.drawable.ic_trashcan, R.drawable.ic_plant};
        for (int i=0; i<imgs.length;i++) {
            gamesItemArrayList.add(new GamesItem(imgs[i] ,titles[i]));
        }
    }

    private void setUpRecyclerView() {
        layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.gamelistRecyclerview.setLayoutManager(layoutManager);
        adapter = new GamesRecyclerAdapter(getApplicationContext(),gamesItemArrayList);
        binding.gamelistRecyclerview.setAdapter(adapter);

    }
}
