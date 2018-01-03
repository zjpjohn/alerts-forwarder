package com.chinamobile.cmss.service;

import com.chinamobile.cmss.domain.WWWAlert;


/**
 * @author 杜奎
 * @date 2017/11/08
 * @version v0.0.1
 */

public interface WWWAlertService {
	/*
	 * encapsulate DDL
	 */
	public void create(String tableName);
	
	public void clear(String tableName);
	
	public void drop(String tableName);
	
	/*
	 * encapsulate DML
	 */
	public void insert(WWWAlert wwwAlert);
	
	public void delete(String LocateInfo);
	
	public void update(WWWAlert wwwAlert);
	
	public WWWAlert select(String LocateInfo);
}
