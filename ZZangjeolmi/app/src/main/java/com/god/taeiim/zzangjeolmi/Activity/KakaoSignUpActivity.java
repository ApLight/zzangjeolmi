package com.god.taeiim.zzangjeolmi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.god.taeiim.zzangjeolmi.APIUrl;
import com.god.taeiim.zzangjeolmi.RestAPI;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;

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

public class KakaoSignUpActivity extends Activity {
    private long kakaoID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "SighUpActivity", Toast.LENGTH_LONG).show();
        requestMe();
    }

    private void requestMe() {
        UserManagement.getInstance().me(new MeV2ResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                redirectLoginActivity();
            }

            @Override
            public void onSuccess(MeV2Response result) {
                kakaoID = result.getId();
                Logger.d("user id : " + result.getId());
                Logger.d("email: " + result.getKakaoAccount().getEmail());
                Log.d("result.toString() : ", result.toString());
                Log.d("result id : ", String.valueOf(result.getId()));



                getUserInfoFromServer();
            }
        });
    }

    public void getUserInfoFromServer() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();

        Retrofit builder = new Retrofit.Builder()
                .baseUrl(APIUrl.API_BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestAPI restAPI = builder.create(RestAPI.class);

        Call<JsonObject> call = restAPI.login(kakaoID);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("response code ====", String.valueOf(response.code()));
                if (response.code() == 200) {
                    Log.d("response----", response.body().toString());
                    JsonObject firstJsonObject = response.body().getAsJsonObject();
                    JsonObject accoutObject = firstJsonObject.getAsJsonObject("account");

                    int level = accoutObject.getAsJsonPrimitive("level").getAsInt();
                    int exp = accoutObject.getAsJsonPrimitive("exp").getAsInt();
                    int userID = accoutObject.getAsJsonPrimitive("id").getAsInt();

                    SharedPreferences pref = getSharedPreferences("userID", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putLong("userID", userID);
                    editor.commit();

                    redirectMainActivity(level, exp, kakaoID);

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("ERROR! onFailure!", t.toString());
            }
        });
    }


    public void redirectMainActivity(int level, int exp, long kakaoID) {
        Intent intent = new Intent(KakaoSignUpActivity.this, MainActivity.class);
        intent.putExtra("level",level+1);
        intent.putExtra("exp",exp);
        startActivity(intent);
        finish();
    }

    public void redirectLoginActivity() {
        startActivity(new Intent(KakaoSignUpActivity.this, LoginActivity.class));
        finish();
    }
}