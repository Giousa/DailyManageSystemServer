package com.giousa.daily.bk;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test2 {

    @Test
    public void tset(){
        Map<String,List<String>> map = new HashMap<>();
        map.put("a", Arrays.asList("aa"));
        map.put("b", Arrays.asList("bb","fff"));
        map.put("c", Arrays.asList("cc","gggg"));
        map.put("d", Arrays.asList("dd","221","1111"));

        System.out.println(map);
    }
}
