package com.chinamobile.cmss.domain;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * @author 杜奎
 * @date 2017/11/09
 * @version v0.0.1
 * @description WWWAlert类负责封装天津综合监控系统需求告警信息
 *
 */

@Component
public class WWWAlert implements Serializable{
	
	private static final long serialVersionUID = -4911170728929444063L;
	
	private long id;
	private String IntVersion;		 // 接口版本号
	private String AlarmUniqueId;	 // 网管告警唯一标识
	private String NeName;		     // 网元名称
	private String SystemName;	     // 告警来源
	private String EquipmentType;	 // 设备类型
	private String Vendor;	         // 厂家标识
	private String Version;	         // 网元版本
	private String LocateNeName;	 // 告警定位对象
	private String LocateNeType;	 // 告警定位对象的类型
	private String LocateInfo;	     // 告警正文中定位信息
	private String EventTime;	     // 告警事件发生时间
	private String CancelTime;	     // 告警清除时间
	private String AlarmType;	     // 网管告警类型
	private String VendorAlarmType;	 // 厂家告警类型
	private String VendorSeverity;	 // 厂家告警级别
	private String AlarmSeverity;	 // 网管告警级别
	private String VendorAlarmId;    // 厂家告警ID
	private String AlarmStatus;	     // 网管告警状态：0：清除告警、1：活动告警、2：同步清除
	private String AlarmTitle;       // 网管告警标题
	private String ProbableCauseTxt; // 网管告警可能产生原因
	private String AlarmText;	     // 网管告警正文
	private String job;
	private String instance;
	
	public WWWAlert() {
		
	}
	
	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id=id;
	}
	public String getIntVersion() {
		return IntVersion;
	}
	public void setIntVersion(String intVersion) {
		IntVersion = intVersion;
	}
	public String getAlarmUniqueId() {
		return AlarmUniqueId;
	}
	public void setAlarmUniqueId(String alarmUniqueId) {
		AlarmUniqueId = alarmUniqueId;
	}
	public String getNeName() {
		return NeName;
	}
	public void setNeName(String neName) {
		NeName = neName;
	}
	public String getSystemName() {
		return SystemName;
	}
	public void setSystemName(String systemName) {
		SystemName = systemName;
	}
	public String getEquipmentType() {
		return EquipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		EquipmentType = equipmentType;
	}
	public String getVendor() {
		return Vendor;
	}
	public void setVendor(String vendor) {
		Vendor = vendor;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getLocateNeName() {
		return LocateNeName;
	}
	public void setLocateNeName(String locateNeName) {
		LocateNeName = locateNeName;
	}
	public String getLocateNeType() {
		return LocateNeType;
	}
	public void setLocateNeType(String locateNeType) {
		LocateNeType = locateNeType;
	}
	public String getLocateInfo() {
		return LocateInfo;
	}
	public void setLocateInfo(String locateInfo) {
		LocateInfo = locateInfo;
	}
	public String getEventTime() {
		return EventTime;
	}
	public void setEventTime(String eventTime) {
		EventTime = eventTime;
	}
	public String getCancelTime() {
		return CancelTime;
	}
	public void setCancelTime(String cancelTime) {
		CancelTime = cancelTime;
	}
	public String getVendorAlarmType() {
		return VendorAlarmType;
	}
	public void setVendorAlarmType(String vendorAlarmType) {
		VendorAlarmType = vendorAlarmType;
	}
	public String getVendorSeverity() {
		return VendorSeverity;
	}
	public void setVendorSeverity(String vendorSeverity) {
		VendorSeverity = vendorSeverity;
	}
	public String getAlarmSeverity() {
		return AlarmSeverity;
	}
	public void setAlarmSeverity(String alarmSeverity) {
		AlarmSeverity = alarmSeverity;
	}
	public String getVendorAlarmId() {
		return VendorAlarmId;
	}
	public void setVendorAlarmId(String vendorAlarmId) {
		VendorAlarmId = vendorAlarmId;
	}
	public String getAlarmStatus() {
		return AlarmStatus;
	}
	public void setAlarmStatus(String alarmStatus) {
		AlarmStatus = alarmStatus;
	}
	public String getAlarmTitle() {
		return AlarmTitle;
	}
	public void setAlarmTitle(String alarmTitle) {
		AlarmTitle = alarmTitle;
	}
	public String getProbableCauseTxt() {
		return ProbableCauseTxt;
	}
	public void setProbableCauseTxt(String probableCauseTxt) {
		ProbableCauseTxt = probableCauseTxt;
	}
	public String getAlarmText() {
		return AlarmText;
	}
	public void setAlarmText(String alarmText) {
		AlarmText = alarmText;
	}
	public String getAlarmType() {
		return AlarmType;
	}
	public void setAlarmType(String alarmType) {
		AlarmType = alarmType;
	}
	public String getJob() {
		return this.job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getInstance() {
		return this.instance;
	}
	public void setInstance(String instance) {
		this.instance = instance;
	}
	
	@Override
	public String toString() {
		return "<AlarmStart>\r\n" + 
				"  IntVersion:" + this.IntVersion + "\r\n" +
				"  AlarmUniqueId:" + this.AlarmUniqueId + "\r\n" +
				"  NeName:" + this.NeName + "\r\n" +
				"  SystemName:" + this.SystemName + "\r\n" +
				"  EquipmentType:" + this.EquipmentType + "\r\n" +
				"  Vendor:" + this.Vendor + "\r\n" +
				"  Version:" + this.Version + "\r\n" +
				"  LocateNeName:" + this.LocateNeName + "\r\n" +
				"  LocateNeType:" + this.LocateNeType + "\r\n" +
				"  LocateInfo:" + this.LocateInfo + "\r\n" +
				"  EventTime:" + this.EventTime + "\r\n" +
				"  CancelTime:" + this.CancelTime + "\r\n" +
				"  VendorAlarmType:" + this.VendorAlarmType + "\r\n" +
				"  AlarmType:" + this.AlarmType + "\r\n" +
				"  VendorSeverity:" + this.VendorSeverity + "\r\n" +
				"  AlarmSeverity:" + this.AlarmSeverity + "\r\n" +
				"  VendorAlarmId:" + this.VendorAlarmId + "\r\n" +
				"  AlarmStatus:" + this.AlarmStatus + "\r\n" +
				"  AlarmTitle:" + this.AlarmTitle + "\r\n" +
				"  ProbableCauseTxt:" + this.ProbableCauseTxt + "\r\n" +
				"  AlarmText:" + this.AlarmText + "\r\n" +
				"<AlarmEnd>\r\n";
	}

}
