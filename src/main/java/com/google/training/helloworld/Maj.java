package com.google.training.helloworld;

public class Maj {
    public String message = "Hello Endpoints";

    public Maj () {
    }

    public Maj (String name) {
        message = name;
    }
  
    public Maj(String name, String period ) {
        message = "Good " + period + " " + name;
    }

    public String getMessage() {
        return message;
    }
}
