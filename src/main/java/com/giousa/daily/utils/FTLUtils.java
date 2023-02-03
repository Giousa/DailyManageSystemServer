package com.giousa.daily.utils;

import org.apache.commons.lang3.StringUtils;

public class FTLUtils {

    /**
     * 蛇形转驼峰
     */
    public static String snakeToCamel(String snakeStr) {
        if (StringUtils.isBlank(snakeStr) || !snakeStr.contains("_")) {
            return snakeStr;
        }
        StringBuilder sb = new StringBuilder();
        char[] chars = snakeStr.toCharArray();
        boolean is = false;
        for (char c : chars) {
            if ('_' == c) {
                is = true;
            } else if (is) {
                sb.append(Character.toUpperCase(c));
                is = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 蛇形转首字母大写驼峰
     */
    public static String snakeToUpperCamel(String snakeStr) {
        String camelStr = snakeToCamel(snakeStr);
        if (StringUtils.isBlank(camelStr)) {
            return snakeStr;
        }
        char[] cs = camelStr.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    /**
     * 驼峰转蛇形
     */
    public static String camelToSnake(String camelStr) {
        if (null == camelStr || camelStr.length() <= 1) {
            return camelStr;
        }
        StringBuilder sb = new StringBuilder(camelStr.length());
        char[] chars = camelStr.toCharArray();
        for (char c : chars) {
            if (Character.isUpperCase(c)) {
                sb.append("_");
            }
            sb.append(c);
        }
        return sb.toString().toLowerCase();
    }

    public static String sqlToType(String sqlType){
        if(StringUtils.isBlank(sqlType)){
            return null;
        }

        switch (sqlType){
            case "BIGINT UNSIGNED":
                return "Long";

            case "INT":
                return "Integer";

            case "VARCHAR":
                return "String";

            case "TEXT":
                return "String";

            case "DATE":
                return "Date";

            case "DATETIME":
                return "Date";
        }

        return null;
    }

    public static void main(String[] args) {
        String ftl_record = snakeToCamel("ftl_record");
        String ftl_record_upper = snakeToUpperCamel("ftl_record");
        String teaRecord = camelToSnake("teaRecord");

        System.out.println("ftl_record = "+ftl_record);
        System.out.println("ftl_record_upper = "+ftl_record_upper);
        System.out.println("teaRecord = "+teaRecord);

        String bigint_unsigned = sqlToType("BIGINT UNSIGNED");
        String varchar = sqlToType("VARCHAR");
        System.out.println("bigint_unsigned = "+bigint_unsigned);
        System.out.println("varchar = "+varchar);

    }
}
