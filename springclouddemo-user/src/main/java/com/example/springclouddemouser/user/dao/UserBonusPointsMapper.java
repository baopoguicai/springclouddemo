package com.example.springclouddemouser.user.dao;

import com.example.springclouddemouser.user.entity.UserBonusPoints;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserBonusPointsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBonusPoints record);

    int insertSelective(UserBonusPoints record);

    UserBonusPoints selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserBonusPoints record);

    int updateByPrimaryKey(UserBonusPoints record);
}
