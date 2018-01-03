package com.chinamobile.cmss.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.chinamobile.cmss.domain.WWWAlert;

/**
 * @author 杜奎
 * @date 2017/11/10
 * @version v0.0.1
 */

public interface WWWAlertMapper {
	
	/*
	 * keyColumn支持数据库较少，尽量使用keyProperty属性
	 * useGeneratedKey属性并不是说主键由Mybatis生成（事实上，Mybatis是无法生成主键的），其指使用JDBC的getGenereatedKeys方法获取主键，
	 * 然后绑定到keyProperty指定的相应实例中的属性（id）中
	 */
	@Options(keyProperty="id",useGeneratedKeys=true)
	@Insert("insert into alerts("
				+ "id,"
				+ "IntVersion,"
				+ "AlarmUniqueId,"
				+ "NeName,"
				+ "SystemName,"
				+ "EquipmentType,"
				+ "Vendor,"
				+ "LocateNeName,"
				+ "LocateNeType,"
				+ "LocateInfo,"
				+ "EventTime,"
				+ "CancelTime,"
				+ "AlarmType,"
				+ "VendorAlarmType,"
				+ "VendorSeverity,"
				+ "VendorAlarmId,"
				+ "AlarmStatus,"
				+ "AlarmTitle,"
				+ "ProbableCauseTxt,"
				+ "AlarmText,"
				+ "job,"
				+ "instance) "
			+ "values ("
				+ "#{id},"
				+ "#{IntVersion},"
				+ "#{AlarmUniqueId},"
				+ "#{NeName},"
				+ "#{SystemName},"
				+ "#{EquipmentType},"
				+ "#{Vendor},"
				+ "#{LocateNeName},"
				+ "#{LocateNeType},"
				+ "#{LocateInfo},"
				+ "#{EventTime},"
				+ "#{CancelTime},"
				+ "#{AlarmType},"
				+ "#{VendorAlarmType},"
				+ "#{VendorSeverity},"
				+ "#{VendorAlarmId},"
				+ "#{AlarmStatus},"
				+ "#{AlarmTitle},"
				+ "#{ProbableCauseTxt},"
				+ "#{AlarmText},"
				+ "#{job},"
				+ "#{instance})"
			)
	public void insert(WWWAlert wwwAlert);
	
	@Delete("delete from alerts where LocateInfo = #{LocateInfo}")
	public void delete(String LocateInfo);
	
	@Update("update alerts set AlarmUniqueId = #{AlarmUniqueId}, EventTime = #{EventTime}, AlarmText = #{AlarmText} where LocateInfo = #{LocateInfo}")
	public void update(WWWAlert wwwAlert);
	
	@Select("select * from alerts where LocateInfo = #{LocateInfo}")
	public WWWAlert select(String LocateInfo);
	
	@Select("select * from alerts")
	public List<WWWAlert> getAll();
}
