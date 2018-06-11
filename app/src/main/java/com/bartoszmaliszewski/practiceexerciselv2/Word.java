package com.bartoszmaliszewski.practiceexerciselv2;

/**
 * Created by bartoszmaliszewski on 19.02.18.
 */

public class Word {

    private int id;
    private String engWord;
    private String plWord;
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
    public void setEngWord(String engWord) {
        this.engWord = engWord;
    }
    public String getEngWord() {
        return this.engWord;
    }
    public void setPlWord(String plWord) {
        this.plWord = plWord;
    }
    public String getPlWord() {
        return this.plWord;
    }
}
