package com.god.taeiim.zzangjeolmi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;

/**
 * Created by parktaeim on 2018. 9. 1..
 */

public class KakaoSignUpActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this,"SighUpActivity",Toast.LENGTH_LONG).show();
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
                Logger.d("user id : " + result.getId());
                Logger.d("email: " + result.getKakaoAccount().getEmail());
                redirectMainActivity();
            }
        });
    }

    public void redirectMainActivity(){
        Intent intent = new Intent(KakaoSignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void redirectLoginActivity(){
        startActivity(new Intent(KakaoSignUpActivity.this,LoginActivity.class));
        finish();
    }
}