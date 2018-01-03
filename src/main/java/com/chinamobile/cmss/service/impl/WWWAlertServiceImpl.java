package com.chinamobile.cmss.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chinamobile.cmss.dao.WWWAlertDao;
import com.chinamobile.cmss.domain.WWWAlert;
import com.chinamobile.cmss.service.WWWAlertService;

/**
 * @author 杜奎
 * @date 2017/11/9
 * @version v0.0.1
 */

@Component
public class WWWAlertServiceImpl implements WWWAlertService{
	
	@Autowired
	private WWWAlertDao wwwAlertDao;
	
	public void insert(WWWAlert wwwAlert) {
		wwwAlertDao.insert(wwwAlert);
	}
	
	public void delete(String LocateInfo) {
		wwwAlertDao.delete(LocateInfo);
	}
	
	public void update(WWWAlert wwwAlert) {
		wwwAlertDao.update(wwwAlert);
	}
	
	public WWWAlert select(String LocateInfo) {
		return wwwAlertDao.select(LocateInfo);
	}

	// 创建表
	public void create(String tableName) {
		wwwAlertDao.createIfNotExistsTable(tableName);
		
	}
	
	// 清空表
	public void clear(String tableName) {
		wwwAlertDao.truncateTable(tableName);
	}
	
	// 删除表
	public void drop(String tableName) {
		wwwAlertDao.dropIfExistsTable(tableName);
	}
}
