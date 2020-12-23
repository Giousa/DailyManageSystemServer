package com.giousa.daily.service.impl;

import com.giousa.daily.bean.ResultVO;
import com.giousa.daily.bean.comp.QtCompanyEmployeBody;
import com.giousa.daily.bean.comp.QtCompanyEmployeVO;
import com.giousa.daily.enums.ResultEnum;
import com.giousa.daily.mapper.QtCompanyEmployeMapper;
import com.giousa.daily.model.QtCompanyEmploye;
import com.giousa.daily.service.QtCompanyEmployeService;
import com.giousa.daily.utils.DataHandlerUtils;
import com.giousa.daily.utils.DateUtil;
import com.giousa.daily.utils.KeyUtil;
import com.giousa.daily.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2020/12/23
 * Email:65489469@qq.com
 */
@Service
public class QtCompanyEmployeServiceImpl implements QtCompanyEmployeService {

    @Autowired
    private QtCompanyEmployeMapper qtCompanyEmployeMapper;

    @Override
    public ResultVO addOrUpdateQtCompanyEmploye(QtCompanyEmployeBody qtCompanyEmployeBody) {

        String companyId = qtCompanyEmployeBody.getCompanyId();
        String userId = qtCompanyEmployeBody.getUserId();
        Integer levelId = qtCompanyEmployeBody.getLevelId();

        if(StringUtil.isEmpty(companyId) || StringUtil.isEmpty(userId) || levelId == null){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }



        String id = qtCompanyEmployeBody.getId();

        QtCompanyEmploye qtCompanyEmploye = new QtCompanyEmploye();

        BeanUtils.copyProperties(qtCompanyEmployeBody,qtCompanyEmploye);

        String entryDt = qtCompanyEmployeBody.getEntryDt();
        String quitDt = qtCompanyEmployeBody.getQuitDt();
        if(StringUtil.isEmpty(entryDt)){
            try {
                Date date = DateUtil.stringToDate(entryDt, "yyyy-MM-dd");
                qtCompanyEmploye.setEntryDt(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(StringUtil.isEmpty(quitDt)){
            try {
                Date date = DateUtil.stringToDate(quitDt, "yyyy-MM-dd");
                qtCompanyEmploye.setQuitDt(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        if(StringUtil.isEmpty(id)){
            qtCompanyEmploye.setId(KeyUtil.getKeyId());
            qtCompanyEmploye.setStatus(1);
            qtCompanyEmploye.setCreateTime(new Date());
            qtCompanyEmploye.setUpdateTime(new Date());

            qtCompanyEmployeMapper.insert(qtCompanyEmploye);

            return ResultVO.ok("添加成功");
        }else {

            QtCompanyEmploye qtCompanyEmployeDB = qtCompanyEmployeMapper.selectByPrimaryKey(id);
            if(qtCompanyEmployeDB == null){
                return ResultVO.error(ResultEnum.ID_IS_WRONG);
            }

            qtCompanyEmploye.setUpdateTime(new Date());

            qtCompanyEmployeMapper.updateByPrimaryKeySelective(qtCompanyEmploye);

            return ResultVO.ok("更新成功");
        }

    }

    @Override
    public ResultVO findQtCompanyEmployeList(String companyId, Integer levelId, int page, int size) {

        List<QtCompanyEmployeVO> list = qtCompanyEmployeMapper.findQtCompanyEmployeList(companyId,levelId,(page-1)*size,size);
        int totalCount = qtCompanyEmployeMapper.findQtCompanyEmployeListTotalCount(companyId,levelId);

        return ResultVO.ok(DataHandlerUtils.handlePage(list,page,size,totalCount));
    }

    @Override
    public ResultVO findQtCompanyEmployeInfo(String id) {

        QtCompanyEmployeVO qtCompanyEmployeVO = qtCompanyEmployeMapper.findQtCompanyEmployeInfo(id);
        return ResultVO.ok(qtCompanyEmployeVO);
    }

    @Override
    public ResultVO deleteQtCompanyEmploye(String id) {

        QtCompanyEmploye qtCompanyEmployeDB = qtCompanyEmployeMapper.selectByPrimaryKey(id);
        if(qtCompanyEmployeDB == null){
            return ResultVO.error(ResultEnum.ID_IS_WRONG);
        }

        qtCompanyEmployeMapper.deleteByPrimaryKey(id);
        return ResultVO.ok("删除成功");
    }
}
