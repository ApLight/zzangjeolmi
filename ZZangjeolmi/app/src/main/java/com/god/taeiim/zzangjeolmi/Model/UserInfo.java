package com.god.taeiim.zzangjeolmi.Model;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 9. 2..
 */

public class UserInfo {
    private int currentLevel;
    private int currentExp;
    private int currentLevelMaxExp;
    ArrayList<Integer> maxExpArrayList;

    public UserInfo(){
        setUpCurrentLevelMaxExp();
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(int currentExp) {
        this.currentExp = currentExp;
    }

    public int getCurrentLevelMaxExp() {
        return maxExpArrayList.get(getCurrentLevel());
    }

    private void setUpCurrentLevelMaxExp(){
        int num = 50;
        maxExpArrayList = new ArrayList<>();
        maxExpArrayList.add(0);
        for(int i=0;i<100;i++){
            maxExpArrayList.add(num);
            num+=5;
        }
    }
}
