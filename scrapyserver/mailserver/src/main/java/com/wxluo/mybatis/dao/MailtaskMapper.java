package com.wxluo.mybatis.dao;

import com.wxluo.mybatis.entity.Mailtask;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MailtaskMapper {
    int deleteByPrimaryKey(Integer mailID);

    int insert(Mailtask record);

    Mailtask selectByPrimaryKey(Integer mailID);

    List<Mailtask> selectAll();

    int updateByPrimaryKey(Mailtask record);
}