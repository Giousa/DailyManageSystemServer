package com.giousa.daily.service.impl;

import com.giousa.daily.bean.ResultVO;
import com.giousa.daily.enums.ResultEnum;
import com.giousa.daily.mapper.QtSortMapper;
import com.giousa.daily.model.QtSort;
import com.giousa.daily.service.QtSortService;
import com.giousa.daily.utils.DataHandlerUtils;
import com.giousa.daily.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2020/12/23
 * Email:65489469@qq.com
 */
@Service
public class QtSortServiceImpl implements QtSortService {

    @Autowired
    private QtSortMapper qtSortMapper;

    @Override
    public ResultVO addQtSort(QtSort qtSort) {

        Integer id = qtSort.getId();
        String type = qtSort.getType();
        String name = qtSort.getName();
        Integer weight = qtSort.getWeight();
        if(id == null || StringUtil.isEmpty(type) || StringUtil.isEmpty(name) || weight == null){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }

        qtSortMapper.insert(qtSort);

        return ResultVO.ok("添加成功");

    }

    @Override
    public ResultVO updateQtSort(QtSort qtSort) {

        Integer id = qtSort.getId();
        String type = qtSort.getType();
        String name = qtSort.getName();
        Integer weight = qtSort.getWeight();
        if(id == null || StringUtil.isEmpty(type) || StringUtil.isEmpty(name) || weight == null){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }

        QtSort qtSortDB = qtSortMapper.selectByPrimaryKey(id);
        if(qtSortDB == null){
            return ResultVO.error(ResultEnum.ID_IS_WRONG);
        }

        qtSortMapper.updateByPrimaryKey(qtSort);
        return ResultVO.ok("修改成功");

    }

    @Override
    public ResultVO findQtSortList(String type, String name) {

        List<QtSort> list = qtSortMapper.findQtSortList(type, DataHandlerUtils.sqlLikeStr(name));

        return ResultVO.ok(list);
    }

    @Override
    public ResultVO findQtSortInfo(Integer id) {
        return ResultVO.ok(qtSortMapper.selectByPrimaryKey(id));
    }

    @Override
    public ResultVO deleteQtSort(Integer id) {

        QtSort qtSortDB = qtSortMapper.selectByPrimaryKey(id);
        if(qtSortDB == null){
            return ResultVO.error(ResultEnum.ID_IS_WRONG);
        }

        qtSortMapper.deleteByPrimaryKey(id);
        return ResultVO.ok("删除成功");
    }
}
