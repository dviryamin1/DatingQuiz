package com.DvirDev.AlizasGame;

public class ScreenItem {
    String Title, question;

    public ScreenItem(String title, String description) {
        Title = title;
        question = description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
