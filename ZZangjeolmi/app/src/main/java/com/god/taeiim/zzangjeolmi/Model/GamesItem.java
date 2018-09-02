package com.god.taeiim.zzangjeolmi.Model;

import android.graphics.drawable.Drawable;

/**
 * Created by parktaeim on 2018. 9. 1..
 */

public class GamesItem {
    private int img;
    private String gameName;

    public GamesItem(int imgUrl, String gameName) {
        this.img = imgUrl;
        this.gameName = gameName;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
