package com.example.springclouddemouser.message.dao;

import com.example.springclouddemouser.message.entity.MessageEventProcess;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageEventProcessMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessageEventProcess record);

    int insertSelective(MessageEventProcess record);

    MessageEventProcess selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageEventProcess record);

    int updateByPrimaryKey(MessageEventProcess record);

}
