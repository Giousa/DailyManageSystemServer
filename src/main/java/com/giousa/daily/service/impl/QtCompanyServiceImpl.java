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

        String fullName = qtCompany.getFullName();
        String city = qtCompany.getCity();
        if(StringUtil.isEmpty(fullName) || StringUtil.isEmpty(city)){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }

        String id = qtCompany.getId();
        if(StringUtil.isEmpty(id)){
            //new
            qtCompany.setId(KeyUtil.getKeyId());

            qtCompanyMapper.insert(qtCompany);

            return ResultVO.ok("创建成功");
        }else {
            //update
            QtCompany qtCompanyDB = qtCompanyMapper.selectByPrimaryKey(id);
            if(qtCompanyDB == null){
                return ResultVO.error(ResultEnum.ID_IS_WRONG);
            }

            qtCompany.setCreateTime(qtCompanyDB.getCreateTime());

            qtCompanyMapper.updateByPrimaryKey(qtCompany);

            return ResultVO.ok("修改成功");
        }
    }

    @Override
    public ResultVO findCompanyList() {
        return null;
    }

    @Override
    public ResultVO findCompanyInfo(String id) {
        return null;
    }

    @Override
    public ResultVO deleteCompany(String id) {
        return null;
    }
}
