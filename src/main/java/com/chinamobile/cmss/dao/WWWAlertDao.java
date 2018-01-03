package com.chinamobile.cmss.dao;

import com.chinamobile.cmss.domain.WWWAlert;


/**
 * @author 杜奎
 * @date 2017/11/08
 * @version v0.0.1
 */

public interface WWWAlertDao {
	
	/*
	 * DDL
	 */
	public void createIfNotExistsTable(String tableName);
	
	public void truncateTable(String tableName);
	
	public void dropIfExistsTable(String tableName);

	/*
	 * DML
	 */
	public void insert(WWWAlert wwwAlert);
	
	public void delete(String LocateInfo);
	
	public void update(WWWAlert wwwAlert);
	
	public WWWAlert select(String LocateInfo);
}
