package com.giousa.daily.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test02 {

    public static void main(String[] args) {
        List<User> list = Arrays.asList(
                new User("1111","曹操",13),
                new User("2222","刘备",42),
                new User("3333","孙权",444),
                new User("4444","司马懿",133),
                new User("4444","袁绍",22),
                new User("3333","陶谦",44)
        );

        //获取用户groupId和groupName集合
        Map<String, String> map = list.stream().collect(Collectors.toMap(User::getId, User::getName, (t1, t2) -> t1));
        System.out.println("map = "+map);
        if(map.size() <= 0){
            System.out.println("数据为空");
            return;
        }
        //获取用户所有groupId列表
        List<String> groupIdList = map.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println("groupIdList = "+groupIdList);
        //获取用户全部权益
        list.stream().map(it -> EquityConvert.convert(it,map.get(it.getId()))).collect(Collectors.toList());

    }

}
