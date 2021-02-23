package com.giousa.daily.test;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2021/2/19
 */
public class SupplierTest {

    public static void main(String[] args) {
        System.out.println("dev 2021-02-23 21:48:34");

        System.out.println("master 2021-02-23 21:50:48");

        System.out.println("master 2021-02-23 21:51:48");

        System.out.println("temp 2021-02-23 22:03:10");

//        User user1 = new User("fff", 11);

        Supplier<User> user = () -> new User("aa",11);
        System.out.println( user.get());

        System.out.println("-----------------------------");
        List<User> list = new ArrayList<>();
        list.add(new User("AA",11));
        list.add(new User("BB",22));
        list.add(new User("CC",33));
        list.add(new User("DD",44));
        list.add(new User(null,0));

        list.forEach(it -> System.out.println(it.getName()));

        System.out.println("-----------------------------");

        Map<String,Integer> map = new HashMap<>();
        map.put("aa",11);
        map.put("bb",22);
        map.put("cc",33);
        map.put("dd",44);

        map.forEach((key,value) -> System.out.println(key+" --  "+value));

        System.out.println("-----------------------------");


        List<String> list2 = new ArrayList<>();
        map.forEach((key,value) -> list2.add(key));

        System.out.println(list2);
        System.out.println("-----------------------------");

//        Map<String,MapObj> map2 = new HashMap<>();
//        map.forEach(it -> map2.put(it,new MapObj(it)));

//        List<User> collect = list.stream().filter(l -> l.getAge() > 11).collect(Collectors.toList());
//
//        System.out.println(collect);


        List<User> collect = list.stream()
                .filter(Objects::nonNull)
                .map(SupplierTest::mapTest)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        System.out.println(collect);

        System.out.println("-----------------------------");


        params("aa",11,"bb",22,new User("ff"));



    }

    private static User mapTest(User user){
        System.out.println("测试用户转换："+user);

        user.setAge(user.getAge()+100);
        return user;
    }

    private static void params(String name,Integer age,Object... params){

        Object obj = name+"-"+age+"-"+params;
        System.out.println(params.toString());
        System.out.println(obj);
    }


}


