package com.god.taeiim.zzangjeolmi.Activity;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.god.taeiim.zzangjeolmi.APIUrl;
import com.god.taeiim.zzangjeolmi.Model.QuizItem;
import com.god.taeiim.zzangjeolmi.R;
import com.god.taeiim.zzangjeolmi.RestAPI;
import com.god.taeiim.zzangjeolmi.databinding.ActivityQuizgameBinding;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by parktaeim on 2018. 9. 1..
 */

public class QuizGameActivity extends AppCompatActivity {
    ActivityQuizgameBinding binding;
    ArrayList<QuizItem> quizItemArrayList = new ArrayList<>();
    int statusQuizNum = 0;
    int correctNum = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_quizgame);
        binding.setQuizGameActivity(this);

        binding.icClose.setOnClickListener(v->finish());

        getQuizData();
        getQuizData();
        getQuizData();
        getQuizData();

        binding.quizBtnO.setOnClickListener(v-> afterCheckAnswer(true));
        binding.quizBtnX.setOnClickListener(v-> afterCheckAnswer(false));

        binding.btnAnswerOk.setOnClickListener(v->{
            statusQuizNum+=1;
            if(correctNum==3) showCompleteLayout();
            else showQuizLayout();
        });
    }

    private void afterCheckAnswer(boolean isOX){
        checkCorrectAnswer(isOX);
        showAnswerLayout();
    }

    private void checkCorrectAnswer(boolean answer){
        AppCompatRadioButton[] radioButtons = {binding.star1, binding.star2, binding.star3};
        if(answer == quizItemArrayList.get(statusQuizNum).isAnswer()) {
            correctNum += 1;
            radioButtons[correctNum-1].setChecked(true);
        }
    }

    private void showQuizLayout() {
        binding.answerLayout.setVisibility(View.GONE);
        binding.quizLayout.setVisibility(View.VISIBLE);

        setUpQuizData();
    }

    private void showAnswerLayout() {
        binding.answerLayout.setVisibility(View.VISIBLE);
        binding.quizLayout.setVisibility(View.GONE);

        setUpAnswerData();
    }

    private void showCompleteLayout(){
        binding.answerLayout.setVisibility(View.GONE);
        binding.quizLayout.setVisibility(View.GONE);
        binding.completeLayout.setVisibility(View.VISIBLE);

        binding.btnCompleteOk.setOnClickListener(v->finish());

        Retrofit builder = new Retrofit.Builder()
                .baseUrl(APIUrl.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestAPI restAPI = builder.create(RestAPI.class);

        SharedPreferences pref = getSharedPreferences("userID", MODE_PRIVATE);

        Call<JsonObject> call = restAPI.updateExp(30,pref.getLong("userID", 0));
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.code() == 200) {
                    Log.d("response----", response.body().toString());

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("ERROR! onFailure!", t.toString());
            }
        });
    }

    private void setUpAnswerData(){
        String ox;
        boolean isAnswer = quizItemArrayList.get(statusQuizNum).isAnswer();
        if(isAnswer) ox = "O"; else ox="X";

        binding.commentTv.setText(quizItemArrayList.get(statusQuizNum).getComment());
        binding.answerOXtv.setText(ox);
    }
    private void setUpQuizData() {
        binding.quizTv.setText(quizItemArrayList.get(statusQuizNum).getQuiz());
        Glide.with(this).load(quizItemArrayList.get(statusQuizNum).getImgUrl()).into(binding.quizImg);
    }

    public void getQuizData(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();

        Retrofit builder = new Retrofit.Builder()
                .baseUrl(APIUrl.API_BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestAPI restAPI = builder.create(RestAPI.class);

        Call<JsonObject> call = restAPI.quizzes();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("response----", response.body().toString());
                Log.d("response code ====",String.valueOf(response.code()));

                if(response.code() == 200){
                    JsonObject firstJsonObject = response.body().getAsJsonObject();
                    Log.d("jsonobject new ===",firstJsonObject.toString());
                    JsonArray jsonArray = firstJsonObject.getAsJsonArray("quizzes");
                    getArrayList(jsonArray);

                    setUpQuizData();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("ERROR! onFailure!",t.toString());
            }
        });
    }

    private void getArrayList(JsonArray array) {
        ArrayList<QuizItem> arrayList = new ArrayList<>();
        try{
            for(int i=0;i< array.size();i++){
                JsonObject jsonObject = (JsonObject) array.get(i);
                String problem = jsonObject.getAsJsonPrimitive("problem").getAsString();
                boolean answer = jsonObject.getAsJsonPrimitive("answer").getAsBoolean();
                String comment = jsonObject.getAsJsonPrimitive("explanation").getAsString();
                String imgUrl = jsonObject.getAsJsonPrimitive("img_url").getAsString();

                quizItemArrayList.add(new QuizItem(problem,answer,comment,imgUrl));

                Log.d("arrayList",arrayList.toString());
            }
        }catch (Exception e){
        }
    }

}
