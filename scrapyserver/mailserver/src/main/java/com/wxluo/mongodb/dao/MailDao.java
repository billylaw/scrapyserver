package com.wxluo.mongodb.dao;

import java.util.List;

import org.springframework.data.mongodb.core.mapreduce.GroupByResults;

import com.wxluo.mongodb.entity.MailEntity;

public interface MailDao {
	
	void saveMail(MailEntity mail);
	
	List<MailEntity> findMailByUserId(String userid);
	
	List<MailEntity> findMailByKeywords(String keywords);
	
	List<MailEntity> findAllMails();
	
	GroupByResults<MailEntity> findMailsUserId();
}
