package com.chinamobile.cmss.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chinamobile.cmss.dao.WWWAlertDao;
import com.chinamobile.cmss.domain.WWWAlert;
import com.chinamobile.cmss.mapper.DDLMapper;
import com.chinamobile.cmss.mapper.WWWAlertMapper;

/**
 * @author 杜奎
 * @date 2017/11/8
 * @version v0.0.1
 */

@Component
public class WWWAlertDaoImpl implements WWWAlertDao{
	@Autowired
	private WWWAlertMapper wwwAlertMapper;
	
	@Autowired
	private DDLMapper ddlMapper;
	
	/*
	 * DDL
	 */
	public void createIfNotExistsTable(String tableName) {
		ddlMapper.createIfNotExistsTable(tableName);
	}
	
	public void truncateTable(String tableName) {
		ddlMapper.truncateTable(tableName);
	}
	
	public void dropIfExistsTable(String tableName) {
		ddlMapper.dropTable(tableName);
	}
	
	/*
	 * DML
	 */
	public void insert(WWWAlert wwwAlert) {
		wwwAlertMapper.insert(wwwAlert);
	}
	
	public void delete(String LocateInfo) {
		wwwAlertMapper.delete(LocateInfo);
	}
	
	public void update(WWWAlert wwwAlert) {
		wwwAlertMapper.update(wwwAlert);
	}
	
	public WWWAlert select(String LocateInfo) {
		return wwwAlertMapper.select(LocateInfo);
	}
}
