package com.example.breakingbad;

import android.util.Log;

import java.util.List;
import java.util.jar.Attributes;

//domain class
public class Character {

    //all available attributes
    private int char_id;
    private String name;
    private String birthday;
    private List<String> occupation;
    private String img;
    private String status;
    private String nickname;
    private List<Integer> appearance;
    private String portrayed;
    private String category;
    private List<Integer> better_call_saul_appearance;

    public Character(String name, String nickname, String status, String img, int char_id) {
        this.name = name;
        this.nickname = nickname;
        this.status = status;
        this.img = img;
        this.char_id = char_id;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getStatus() {
        return status;
    }

    public String getImg() {
        return img;
    }

    public int getCharId() {
        return char_id;
    }
}
