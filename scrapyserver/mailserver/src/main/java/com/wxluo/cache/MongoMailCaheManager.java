package com.wxluo.cache;

import java.util.Collection;
import java.util.Set;

import com.wxluo.mongodb.entity.MailEntity;
import com.wxluo.mybatis.entity.Cmail;

public class MongoMailCaheManager {
	private static MongoMailCaheManager instanceManager =  new MongoMailCaheManager();	
	private static SyncMap<String, MailEntity> mailMap = new SyncMap<String, MailEntity>();
	
	private static SyncMap<String, SyncMap<String, MailEntity>> mailMapUserID = new SyncMap<String, SyncMap<String,MailEntity>>();
	
	
	/**
	 * 单例模式
	 * @return
	 */
	public synchronized static MongoMailCaheManager getInstance(){
		if(instanceManager == null)
			return new MongoMailCaheManager();
		return instanceManager;
	}
	
	public void setMailMap(MailEntity mail){
		mailMap.put(mail.getEmail(), mail);
	}
	
	public void getMailMapByUserID(String userid) {
		
	}
	
	public MailEntity getMailMap(String mail){
		return mailMap.get(mail);
	}
	
	public Set<String> getKeys(){
		if( mailMap.keySet() == null || mailMap.size() == 0)
			return null ;
		return (Set<String>) mailMap.keySet();
	}
	
	public Collection<MailEntity> getValues(){
		if(mailMap.values() == null || mailMap.size() == 0){
			return null;
		}
		else {
			return mailMap.values();
		}
	}
	
	public int getSize(){
		return mailMap.size();
	}
}
