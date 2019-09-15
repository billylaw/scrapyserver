package com.wxluo.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.wxluo.cache.MailCacheManager;
import com.wxluo.cache.MongoMailCaheManager;
import com.wxluo.mybatis.dao.CmailMapper;
import com.wxluo.mybatis.entity.Cmail;

/**
 * @author wxluo
 *
 */
@Service
public class MailService {

	@Autowired
	private CmailMapper maillMapper;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	/**
	 * 添加邮件信息
	 * @return
	 */
	public boolean addMail(Cmail mail){
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmssfff");		
		long time = Long.parseLong(sdFormat.toString());
		
		mail.setCreateTime(time);
		mail.setUpdateTime(time);
		mail.setStatus((byte)0);
		
		if(maillMapper.insert(mail) > 0){
			return true;
		}else {
			return false;
		}	
	}
	
	/**
	 * 修改邮件信息
	 * @param mail
	 * @return
	 */
	public boolean updateMail(Cmail mail){
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmssfff");		
		long time = Long.parseLong(sdFormat.toString());
		mail.setUpdateTime(time);
		mail.setStatus((byte)0);
		if(maillMapper.updateByPrimaryKey(mail) > 0){
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * 删除邮件信息
	 * @param mail
	 * @return
	 */
	public boolean  deleteMail(Cmail mail){
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmssfff");		
		long time = Long.parseLong(sdFormat.toString());
		mail.setUpdateTime(time);
		mail.setStatus((byte)1);
		if(maillMapper.updateByPrimaryKey(mail) > 0){
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * 所有邮件
	 * @return
	 */
	public Collection<Cmail> getMails(){
		if(MailCacheManager.getInstance().getSize() ==0){
			return null;
		}
		return MailCacheManager.getInstance().getValues();
	}
	
	
	/**
	 * 发送邮件
	 * @throws Exception
	 */
	public void sendMail() throws Exception{
		
		MimeMessage message = null;
		try {
			message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, null);
			Set<String> mailSet = MongoMailCaheManager.getInstance().getKeys();
			String[] mailStrings = (String[]) mailSet.toArray();
			helper.setFrom("631507429@qq.com");	//发送方邮件
			helper.setTo(mailStrings);	//收件箱
			//标题
			helper.setSubject("MailSubject:Do you want");
			//正文
	        StringBuffer sb = new StringBuffer();
            sb.append("<h1>大标题-h1</h1>")
                    .append("<p style='color:#F00'>红色字</p>")
                    .append("<p style='text-align:right'>右对齐</p>");
            helper.setText(sb.toString(), true);
            //附件
            FileSystemResource fileSystemResource=new FileSystemResource(new File("D:\76678.pdf"));
            helper.addAttachment("Introduction", fileSystemResource);
			//发送
            javaMailSender.send(message);
			
		} catch (MessagingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
}
