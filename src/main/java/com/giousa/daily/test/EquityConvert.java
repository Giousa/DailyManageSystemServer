package com.giousa.daily.test;

public class EquityConvert {

    public static User convert(User user,String name){
        User userDTO = new User();

        userDTO.setName(name);
        System.out.println("name = "+name);

        return userDTO;
    }
}
