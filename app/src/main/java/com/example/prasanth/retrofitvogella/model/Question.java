package com.example.prasanth.retrofitvogella.model;

/**
 * Created by Prasanth on 10/28/2016.
 */
// This is used to map the JSON keys to the object by GSON
public class Question {

    String title;
    String link;

    @Override
    public String toString() {
        return (title);
    }
}
