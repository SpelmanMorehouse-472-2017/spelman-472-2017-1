package com.google.training.helloworld;

import java.util.ArrayList;

public class HelloClass {
    //  public String message = "Economics";
    public ArrayList<String> message = new ArrayList<>();
    public int size = 0;


    public HelloClass(ArrayList<String> name) {
        this.message = name;
        this.size = name.size();
    }
    public HelloClass() {
        ArrayList<String> array = new ArrayList<>();
        String name = "Enter in text fields";
        array.add(name);
        this.message = array;
        this.size = array.size();
    }

    public HelloClass(String temp) {
        ArrayList<String> array = new ArrayList<>();
        //temp = "Enter a number for salaries";
        array.add(temp);
        this.message = array;
        this.size = array.size();
    }

    public ArrayList<String> getMessage() {
        return message;
    }

}