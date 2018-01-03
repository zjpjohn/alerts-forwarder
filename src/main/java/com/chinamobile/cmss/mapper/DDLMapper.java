package com.chinamobile.cmss.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author 杜奎
 * @date 2017/11/08
 * @version v0.0.1
 * @description 所谓DDL，即Data Definition Language，其主要指对数据表的操作；
 * 				而DML，即Data Manipulation Language，其主要指对数据表中数据的操作。
 * 				DDL相关操作一定要在DDLMapper中单独注解，切不可放在POJO相关对象的Mapper接口中，
 * 				另外，必须添加@Param("tableName")否则报错： org.apache.ibatis.reflection.ReflectionException: 
 * 				There is no getter for property named 'tableName' in 'class java.lang.String'
 */

public interface DDLMapper {
	
	@Update("create table if not exists ${tableName}("
			+ "id bigint not null,"
			+ "IntVersion varchar(60),"
			+ "AlarmUniqueId varchar(60),"
			+ "NeName varchar(60),"
			+ "SystemName varchar(60),"
			+ "EquipmentType varchar(60),"
			+ "Vendor varchar(60),"
			+ "LocateNeName varchar(60),"
			+ "LocateNeType varchar(60),"
			+ "LocateInfo varchar(60),"
			+ "EventTime varchar(60),"
			+ "CancelTime varchar(60),"
			+ "AlarmType varchar(60),"
			+ "VendorAlarmType varchar(60),"
			+ "VendorSeverity varchar(60),"
			+ "VendorAlarmId varchar(60),"
			+ "AlarmStatus varchar(60),"
			+ "AlarmTitle varchar(200),"
			+ "ProbableCauseTxt varchar(200),"
			+ "AlarmText varchar(200),"
			+ "job varchar(20),"
			+ "instance varchar(20),"
			+ "primary key(id)) character set='utf8'"
		)
	public void createIfNotExistsTable(@Param("tableName")String tableName);

	@Update("truncate table ${tableName}")
	public void truncateTable(@Param("tableName")String tableName);

	@Update("drop table if exists ${tableName}")
	public void dropTable(@Param("tableName")String tableName);
}
