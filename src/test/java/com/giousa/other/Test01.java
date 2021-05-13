package com.giousa.other;

import com.alibaba.fastjson.JSON;
import com.giousa.daily.reply.TagDTO;
import com.giousa.daily.utils.StringUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.Entry.comparingByValue;

public class Test01 {

    @Test
    public void test01(){
        String str = "用户添加{doctorName}医生好友首次开单";

//        String ss = str.replaceAll("\\$\\{doctorName\\}", "曹操");
        String ss = str.replaceAll("\\{doctorName\\}", "曹操");
        System.out.println(ss);

//        List<TagDTO> list = Arrays.asList(new TagDTO(1L,"tag1",1L)
//                ,new TagDTO(2L,"tag2",2L));
        List<TagDTO> list = Lists.newArrayList();
        Map<Long ,String> map2 = list.stream().distinct().collect(Collectors.toMap(t -> t.getId(), t -> t.getTagName(), (v1, v2) -> v1));

        System.out.println(map2.get(1L));

        System.out.println("0---------------0");
        List<String> list2 = Arrays.asList("很高兴，您已成。为我们","www，wwww","很高。兴{doctorName}");
        System.out.println(list2);
//        list2.forEach(it -> it.replaceAll("\\{doctorName\\}", "曹操"));
//        list2.stream().map(it -> it.replaceAll("\\{doctorName\\}", "曹操")).collect(Collectors.toList());
//        System.out.println(list2);

//        String s2 = list2.toString().replaceAll("\\{doctorName\\}", "曹操");
        List<String> texts = list2.stream().filter(Objects::nonNull)
                .map(item -> item.replace("{doctorName}", "曹操")).collect(Collectors.toList());

        System.out.println(texts);

    }
    public void test() {
        //字符串转list<String>
        String str = "asdfghjkl";
        List<String> lis = Arrays.asList(str.split(""));
        for (String string : lis) {
            System.out.println(string);
        }
        //list<String>转字符串
        System.out.println(String.join("", lis));
    }

    @Test
    public void test111(){
        String s = "com.stjk.consultworkbench.web.controller.AIDoctorController";

        String[] split = s.split("\\.");
        System.out.println(split[split.length-1]);
    }

    @Test
    public void test222(){
        ArrayList<TagDTO> tagDTOS = Lists.newArrayList(new TagDTO(11L, "aa", 0L),
                new TagDTO(99L, "aa", 0L),
                new TagDTO(31L, "aa", 0L),
                new TagDTO(1L, "aa", 0L)
                );

        tagDTOS.stream().forEach(it -> {
            System.out.println(it.getId());
        });

        System.out.println("----------0----------");

        tagDTOS.stream()
                .sorted(Comparator.comparing(TagDTO::getId, Comparator.nullsLast(Long::compareTo)).reversed())
                .forEach(it -> {
            System.out.println(it.getId());
        });


    }

    @Test
    public void test333(){
        Map<String,Integer> map = new HashMap<>();
        map.put("message",22);
        map.put("tip",33);
        map.put("card",11);
        map.entrySet().stream().sorted(Collections.reverseOrder(comparingByValue())).forEach(it -> {
            if(Objects.equals(it.getKey(),"message")){
                System.out.println("message");
            }else if(Objects.equals(it.getKey(),"tip")){
                //推送信息素
                System.out.println("tip");
            }else if(Objects.equals(it.getKey(),"card")){
                //推送卡片
                System.out.println("card");
            }
        });
    }


    @Test
    public void test444(){
        Map<IMTypeEnum,Integer> map = new HashMap<>();
        map.put(IMTypeEnum.IM_MESSAGE,22);
        map.put(IMTypeEnum.IM_TIP,33);
        map.put(IMTypeEnum.IM_CARD,11);
        map.entrySet().stream().sorted(Collections.reverseOrder(comparingByValue())).forEach(it -> {
            if(Objects.equals(it.getKey(),IMTypeEnum.IM_MESSAGE)){
                System.out.println("message");
            }else if(Objects.equals(it.getKey(),IMTypeEnum.IM_TIP)){
                //推送信息素
                System.out.println("tip");
            }else if(Objects.equals(it.getKey(),IMTypeEnum.IM_CARD)){
                //推送卡片
                System.out.println("card");
            }
        });


        System.out.println(Lists.newArrayList("ALL"));
    }

    @Test
    public void test555(){
        MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        map.put("key1", "value1");
        map.put("key1", "value2");
        map.put("key1", "value2");

        System.out.println(map);


        MultiValuedMap<String, String> map2 = new HashSetValuedHashMap<>();
        map2.put("key1", "value1");
        map2.put("key1", "value2");
        map2.put("key1", "value3");
        map2.put("key1", "value3");
        System.out.println(map2);


    }

    @Test
    public void test666(){
//        List<String> list = null;
//
//        list.stream().forEach(it -> System.out.println("hello"));

        String format = String.format("%s医生的%s任务已生效", "sss", "哈哈哈");
        System.out.println(format);

    }

    @Test
    public void test777(){
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");

        Stream.iterate(0, i -> i + 1).limit(list.size()).forEach(index -> {
            String s = list.get(index);
            System.out.println(s);
        });
    }

    @Test
    public void test888(){
        Map<String,String> map = new HashMap<>();
        map.put("a","11");
        map.put("b",null);
        map.put("c",null);
        map.put(null,null);

        System.out.println(map);
    }
    @Test
    public void test999(){
        int i = 0;
        do {
            i++;
            System.out.println(i);
        }while (i < 10);
    }


    @Test
    public void compareDate(){
        String beginTime = "2018-07-29 13:42:32";
        String endTime = "2018-07-29 12:26:32";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");

        try {
            Date date1 = format.parse(beginTime);
            Date date2 = format.parse(endTime);

            //date1小于date2返回-1，date1大于date2返回1，相等返回0
            int compareTo = date1.compareTo(date2);

            System.out.println(compareTo);

        } catch (ParseException e) {
            e.printStackTrace();
        }




    }

    @Test
    public void compare2() throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
        int i = Objects.requireNonNull(format.parse("2021-05-06 11:00:00")).compareTo(new Date());
        System.out.println(i);

        String ss = String.format("系统生成的%s任务已开单", "存储");
        System.out.println(ss);

        String s = "[\"推送动态消息1\",\"推送动态消息2\",\"推送动态消息3\"]";
        List<String> strings = JSON.parseArray(s, String.class);
        System.out.println(strings);

//        List<Long> list = Lists.newArrayList(111L,222L,333L,1L,22L,33L,56L,77L,23L);
        List<Long> list = Lists.newArrayList(111L,222L,333L,1L,22L,33L,56L,1L,2L,3L,4L,5L,6L);


        System.out.println("---------");
        Integer pageSize = 3;
        Integer totalSize = list.size();
        Integer totalNum = totalSize/pageSize;
        if(totalSize%pageSize > 0){
            totalNum++;
        }

        System.out.println("totalSize = "+totalSize);
        System.out.println("totalNum  =  "+totalNum);
        int pageNum = 1;
        do {
            if(pageNum == totalNum){
                System.out.println(list.subList((pageNum-1)*pageSize,totalSize));
            }else {
                System.out.println(list.subList((pageNum-1)*pageSize,pageSize*pageNum));

            }
            pageNum++;
        }while (pageNum <= (totalNum));

    }


    @Test
    public void compare3() throws Exception{
        List<Long> list = Lists.newArrayList(111L,222L,333L,1L,22L,33L,56L,1L,2L,3L,4L,5L,6L,7L,8L,9L);
        Integer pageSize = 3;
        Integer totalSize = list.size();
        Integer totalNum = totalSize/pageSize;
        if(totalSize%pageSize > 0){
            totalNum++;
        }
        int pageNum = 1;
        do {

            System.out.println("page = "+(pageNum-1)*pageSize);
//            System.out.println("size = "+pageSize);
            pageNum++;
        }while (pageNum <= (totalNum));

    }


    @Test
    public void compare4() throws Exception{
//        List<Long> list = Lists.newArrayList(111L,222L,333L,1L,22L,33L,56L,1L,2L,3L,4L,5L,6L,7L,8L,9L);

        ArrayList<TagDTO> tagDTOS = Lists.newArrayList(new TagDTO(11L, "tt", 0L),
                new TagDTO(99L, "aa", 0L),
                new TagDTO(31L, "bb", 0L),
                new TagDTO(1L, "11", 0L)
        );

//        String collect = tagDTOS.stream().map(TagDTO::getTagName).collect(Collectors.joining(","));
//        System.out.println(collect);

        ArrayList<TagDTO> tagDTOS2 = Lists.newArrayList();
        String collect2 = tagDTOS2.stream().map(TagDTO::getTagName).collect(Collectors.joining(","));
        System.out.println(collect2);
        if(StringUtil.isEmpty(collect2)){
            System.out.println("collect2数据为空");
        }

    }
}
