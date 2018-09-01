package com.god.taeiim.zzangjeolmi.Model;

/**
 * Created by parktaeim on 2018. 9. 2..
 */

public class QuizItem {
    String quiz;
    boolean answer; // 맞으면 true, 틀리면 false
    String comment;
    String imgUrl;

    public QuizItem(String quiz, boolean answer, String comment, String imgUrl) {

        this.quiz = quiz;
        this.answer = answer;
        this.comment = comment;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
