package com.wxluo.mongodb.dao;

import java.util.Collection;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.wxluo.mongodb.entity.MailEntity;

@Component
public class MailDaoImpl implements MailDao{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<MailEntity> findAllMails(){
		List<MailEntity> tempsEntities =  mongoTemplate.findAll(MailEntity.class);
		return tempsEntities;
	}
	
	@Override
	public List<MailEntity> findMailByUserId(String userid){
		Query query = new Query(Criteria.where("user_id").is(userid));
		List<MailEntity> mails = mongoTemplate.find(query, MailEntity.class);
				
		return mails;		
	}
	
	@Override
	public GroupByResults<MailEntity> findMailsUserId(){		
		GroupBy groupBy = GroupBy.key("user_id"); 
		GroupByResults<MailEntity> results = mongoTemplate.group("spider", groupBy, MailEntity.class);
		return results;
	}
	
	

	@Override
	public void saveMail(MailEntity mail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MailEntity> findMailByKeywords(String keywords) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
