package com.example.userservice;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Util {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("password"));
    }


}
