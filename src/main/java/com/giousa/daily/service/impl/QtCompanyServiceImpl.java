package com.giousa.daily.service.impl;

import com.giousa.daily.bean.ResultVO;
import com.giousa.daily.enums.ResultEnum;
import com.giousa.daily.mapper.QtCompanyMapper;
import com.giousa.daily.model.QtCompany;
import com.giousa.daily.service.QtCompanyService;
import com.giousa.daily.utils.KeyUtil;
import com.giousa.daily.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2020/12/23
 * Email:65489469@qq.com
 */
@Service
public class QtCompanyServiceImpl implements QtCompanyService {

    @Autowired
    private QtCompanyMapper qtCompanyMapper;

    @Override
    public ResultVO addOrUpdateCompany(QtCompany qtCompany) {

        System.out.println(qtCompany);

        String fullName = qtCompany.getFullName();
        String city = qtCompany.getCity();
        if(StringUtil.isEmpty(fullName) || StringUtil.isEmpty(city)){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }

        String id = qtCompany.getId();
        if(StringUtil.isEmpty(id)){
            //new
            qtCompany.setId(KeyUtil.getKeyId());
            qtCompany.setStatus(1);
            qtCompany.setWeight(0);
            qtCompany.setCreateTime(new Date());
            qtCompany.setUpdateTime(new Date());

            qtCompanyMapper.insert(qtCompany);

            return ResultVO.ok("创建成功");
        }else {
            //update
            QtCompany qtCompanyDB = qtCompanyMapper.selectByPrimaryKey(id);
            if(qtCompanyDB == null){
                return ResultVO.error(ResultEnum.ID_IS_WRONG);
            }

            qtCompany.setCreateTime(qtCompanyDB.getCreateTime());
            qtCompany.setUpdateTime(new Date());

            qtCompanyMapper.updateByPrimaryKeySelective(qtCompany);

            return ResultVO.ok("修改成功");
        }
    }

    @Override
    public ResultVO findCompanyList() {

        List<QtCompany> list = qtCompanyMapper.findCompanyList();

        return ResultVO.ok(list);
    }

    @Override
    public ResultVO findCompanyInfo(String id) {

        QtCompany qtCompany = qtCompanyMapper.selectByPrimaryKey(id);
        return ResultVO.ok(qtCompany);
    }

    @Override
    public ResultVO deleteCompany(String id) {

        QtCompany qtCompanyDB = qtCompanyMapper.selectByPrimaryKey(id);
        if(qtCompanyDB == null){
            return ResultVO.error(ResultEnum.ID_IS_WRONG);
        }

        qtCompanyMapper.deleteCompany(id);
        return ResultVO.ok("删除成功");
    }
}
