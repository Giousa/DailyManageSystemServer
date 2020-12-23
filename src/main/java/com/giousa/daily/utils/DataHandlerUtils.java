package com.giousa.daily.utils;


import com.giousa.daily.bean.page.PageBeanVO;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/8/9
 * Email:65489469@qq.com
 */
public class DataHandlerUtils {

    public static <T> PageBeanVO<T> handlePage(List<T> data, int page, int size, int totalCount){

        int pageSize = 0;
        if(data != null && data.size() > 0){
            pageSize = data.size();
        }

        int totalPage;
        if(totalCount % size == 0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount/size+1;
        }

        PageBeanVO<T> pageBeanVO = new PageBeanVO<>();
        pageBeanVO.setPageNum(page);
        pageBeanVO.setPageSize(size);
        pageBeanVO.setTotalCount(totalCount);
        pageBeanVO.setTotalPage(totalPage);
        pageBeanVO.setData(data);


        return pageBeanVO;
    }

    public static String sqlLikeStr(String data){
        String dataStr = null;
        if(!StringUtils.isEmpty(data)){
            dataStr = "%"+data+"%";
        }

        return dataStr;
    }
}
