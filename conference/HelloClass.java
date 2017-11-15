package com.google.training.helloworld;

public class HelloClass {
    public String message = "Hello Endpoints";

    public HelloClass () {
    }

    public HelloClass (String name) {
        message = "Hello " + name + "!";
    }
  
    public HelloClass(String name, String period ) {
        message = "Good " + period + " " + name;
    }

    public String getMessage() {
        return message;
    }
}
