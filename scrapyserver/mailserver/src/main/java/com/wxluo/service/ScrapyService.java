package com.wxluo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxluo.mybatis.dao.CrawlertaskMapper;

import com.wxluo.mybatis.entity.Crawlertask;
import com.wxluo.utils.LogTool;

@Service
public class ScrapyService {
	
	@Autowired
	private CrawlertaskMapper crawlertaskMapper;
	
	
	public boolean doCrawlerTask(Crawlertask task) throws Exception {
		
		//脚本路径
		String pyFilePath="D:\\start.py";
		//解释器路径
		String pyPath="D:\\programfile\\python";
		//传递给python的参数
		String argv = "";
		
		//操作插入数据
		if(crawlertaskMapper.insert(task) > 0) {
			//执行python脚本
			Process process = Runtime.getRuntime().exec(pyPath+ " " + pyFilePath + " " + argv);
			//需要等待吗？
			process.waitFor();		
			return true; 
		}
		return false;
	}
	
	/**
	 * 运行scrapyd后的脚本命令
	 * @param task
	 * @return
	 * @throws Exception
	 */
	public boolean doCrawlerTaskByScrapyd(Crawlertask task) throws Exception{
		
		String keywords = task.getKeywords();
		Integer userid = task.getUserID();
		String cmdString = "curl http://localhost:6800/schedule.json "
				+ "-d project=google_spider_project -d spider=google1 "
				+ "-d search_words=" + keywords +  " -d user_id=" + userid;
		Process process = Runtime.getRuntime().exec(cmdString);
		
		InputStream iStream = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(iStream);
		BufferedReader br = new BufferedReader(isr);
		String cmdContent = br.readLine();
		
		if(cmdContent.contains("ok"))
		{
			LogTool.logInfo("执行爬虫命令成功!返回内容:" + cmdContent);
			return true;
		}else {
			LogTool.logError("执行爬虫命令失败！返回内容：" + cmdContent);
			return false;			
		}

	}
}
