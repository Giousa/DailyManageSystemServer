package com.giousa.daily.bean.page;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/8/9
 * Email:65489469@qq.com
 */
@Data
public class PageBeanVO<T> implements Serializable {

    private int totalCount;//总条目数

    private int totalPage;//总页数

    private int pageNum;//当前页

    private int pageSize;//当前页显示条目数

    private List<T> data;

    public PageBeanVO() {
    }

    public PageBeanVO(int _pageNum, int _pageSize) {
        this.pageNum = _pageNum;
        this.pageSize = _pageSize;
    }

    public PageBeanVO(int _totalCount, int _pageNum, int _pageSize) {
        this.pageNum = _pageNum;
        this.pageSize = _pageSize;
        this.totalCount = _totalCount;
        if(_pageSize > 0 && _totalCount > 0){
            if(totalCount%pageSize>0){
                this.totalPage = totalCount/pageSize +  1;
            }else {
                this.totalPage = totalCount/pageSize;
            }
        }


    }
    public PageBeanVO(int _totalCount, int _totalPage, int _pageNum, int _pageSize) {
        this.totalCount = _totalCount;
        this.totalPage = _totalPage;
        this.pageNum = _pageNum;
        this.pageSize = _pageSize;
    }

    public PageBeanVO(int _totalCount, int _totalPage, int _pageNum, int _pageSize, List<T> _data) {
        this.totalCount = _totalCount;
        this.totalPage = _totalPage;
        this.pageNum = _pageNum;
        this.pageSize = _pageSize;
        this.data = _data;
    }



    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
