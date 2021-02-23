package com.giousa.daily;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class DailyApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("test 我被执行了");
    }

}
