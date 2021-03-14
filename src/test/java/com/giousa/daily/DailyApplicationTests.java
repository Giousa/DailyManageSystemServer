package com.giousa.daily;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ActiveProfiles("dev")
class DailyApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("test 我被执行了");
    }


    @Test
    public void test1(){
        List<User> list = Arrays.asList(
          new User("AA",11),
          new User("BB",22),
          new User("CC",33),
          new User("DD",44)
        );

//        ArrayList<User> list = Lists.newArrayList();


//        ArrayList<User> list = new ArrayList<>();

        //CC 33 false
        //CC 44 true
        System.out.println(list.stream().noneMatch(it -> "CC".equals(it.getName()) && 33 == it.getAge()));

        String s = "很好";
        System.out.println(s.length());

        String s2 = "ss";
        System.out.println(s2.length());

    }
}
