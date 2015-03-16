package com.meigwilym.ycodwrcanu;

public class Song {

    private int _id;
    private String title;
    private String lyrics;
    private String mp3;
    private String ton;

    public Song(){}

    public Song(String title, String lyrics, String mp3, String ton)
    {
        super();
        this.title = title;
        this.lyrics = lyrics;
        this.mp3 = mp3;
        this.ton = ton;
    }

    public void setId(int id){
        this._id = id;
    }

    public int getId()
    {
        return this._id;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getLyrics() {
        return this.lyrics;
    }

    public void setMp3(String mp3) {
        this.mp3 = mp3;
    }

    public String getMp3() {
        return this.mp3;
    }

    public void setTon(String ton) {
        this.ton = ton;
    }

    public String getTon() {
        return this.ton;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}