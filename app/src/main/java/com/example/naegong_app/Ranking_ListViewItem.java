package com.example.naegong_app;

public class Ranking_ListViewItem {

    private int iconDrawble;
    private String contentStr;
    private String titleStr;

    public void setTitle(String title){ titleStr = title; }

    public void setIcon(int icon){ iconDrawble = icon; }

    public void setContent(String content){ contentStr = content; }

    public String getTitle(){ return this.titleStr; }

    public int getIcon(){ return this.iconDrawble; }

    public String getContent(){ return this.contentStr; }

}