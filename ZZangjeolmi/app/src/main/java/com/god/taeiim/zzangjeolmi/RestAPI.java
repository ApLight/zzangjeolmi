package com.god.taeiim.zzangjeolmi;

import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

/**
 * Created by parktaeim on 2018. 9. 2..
 */

public interface RestAPI {
    @GET(APIUrl.QUIZZES_URL)
    Call<JsonObject> quizzes();
}
