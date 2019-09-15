package com.wxluo.mybatis.dao;

import com.wxluo.mybatis.entity.Cmail;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CmailMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(Cmail record);

    Cmail selectByPrimaryKey(Integer ID);

    List<Cmail> selectAll();

    int updateByPrimaryKey(Cmail record);
}