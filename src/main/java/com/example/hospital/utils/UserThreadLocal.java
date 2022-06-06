package com.example.hospital.utils;

import com.example.hospital.entity.User;

public class UserThreadLocal {
    private UserThreadLocal(){};
    private static final ThreadLocal<User> LOCAL=new ThreadLocal<>();
    public static void put(User user){
        LOCAL.set(user);
    }
    public static User get(){
        return LOCAL.get();
    }
    public static void remove(){
        LOCAL.remove();
    }
}
