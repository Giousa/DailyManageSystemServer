package com.giousa.daily.links;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EncodeUtils {

    /**
     * 将字符串中的中文进行编码
     * @param s
     * @return 返回字符串中汉字编码后的字符串
     */
    public static String cnToEncode(String s ){
        char[] ch = s.toCharArray();
        String result = "";
        for(int i=0;i<ch.length;i++){
            char temp = ch[i];
            if(isChinese(temp)){
                try {
                    String encode = URLEncoder.encode(String.valueOf(temp), "utf-8");
                    result = result + encode;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }else{
                result = result+temp;
            }
        }
        return result;
    }

    /**
     * 判断字符是否为汉字
     * @param c
     * @return
     */
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }
}
