package com.giousa.daily.mapper;

import com.giousa.daily.bean.user.QtUserInfoVO;
import com.giousa.daily.model.QtUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QtUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(QtUser record);

    int insertSelective(QtUser record);

    QtUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(QtUser record);

    int updateByPrimaryKey(QtUser record);

    boolean findQtUserExistFlagByPhone(String phone);

    QtUserInfoVO loginUserByPassword(@Param("phone") String phone, @Param("password") String password);

    QtUserInfoVO loginUserBySms(String phone);

    QtUserInfoVO findUserInfo(String id);

    //模糊查询，必须带上param修饰
    List<QtUserInfoVO> findUserList(@Param("name") String name);
}