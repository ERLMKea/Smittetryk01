package com.example.smittetryk01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Smittetryk01Application {

    public static void main(String[] args) {

        //create jdbc connection to database in azure.

        SpringApplication.run(Smittetryk01Application.class, args);
    }

}
