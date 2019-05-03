package com.traffic.bean.ana;

import java.util.List;


public class TrafficParam {
	/**
     * 姓名
     */
    private String name;

    /**
     * 驾驶证号
     */
    private String licenseid;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮编
     */
    private Integer decodes;

    /**
     * 电话号
     */
    private String phonenumber;

    /**
     * 车牌号
     */
    private String licenceplate;

    /**
     * 型号
     */
    private String modeltype;

    /**
     * 制造厂
     */
    private String factoryname;

    /**
     * 生产时间
     */
    private String expiringdate;

    /**
     * 违章日期
     */
    private String violationdate;

    /**
     * 创建时间
     */
    private String createtime;

    /**
     * 违章地点
     */
    private String violationaddress;

    /**
     * 违章记载
     */
    private String violationcontent;
    
    private List<String> punishmentways;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLicenseid() {
		return licenseid;
	}

	public void setLicenseid(String licenseid) {
		this.licenseid = licenseid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getDecodes() {
		return decodes;
	}

	public void setDecodes(Integer decodes) {
		this.decodes = decodes;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getLicenceplate() {
		return licenceplate;
	}

	public void setLicenceplate(String licenceplate) {
		this.licenceplate = licenceplate;
	}

	public String getModeltype() {
		return modeltype;
	}

	public void setModeltype(String modeltype) {
		this.modeltype = modeltype;
	}

	public String getFactoryname() {
		return factoryname;
	}

	public void setFactoryname(String factoryname) {
		this.factoryname = factoryname;
	}

	public String getExpiringdate() {
		return expiringdate;
	}

	public void setExpiringdate(String expiringdate) {
		this.expiringdate = expiringdate;
	}

	public String getViolationdate() {
		return violationdate;
	}

	public void setViolationdate(String violationdate) {
		this.violationdate = violationdate;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getViolationaddress() {
		return violationaddress;
	}

	public void setViolationaddress(String violationaddress) {
		this.violationaddress = violationaddress;
	}

	public String getViolationcontent() {
		return violationcontent;
	}

	public void setViolationcontent(String violationcontent) {
		this.violationcontent = violationcontent;
	}

	public List<String> getPunishmentways() {
		return punishmentways;
	}

	public void setPunishmentways(List<String> punishmentways) {
		this.punishmentways = punishmentways;
	}

	@Override
	public String toString() {
		return "TrafficParam [name=" + name + ", licenseid=" + licenseid + ", address=" + address + ", decodes="
				+ decodes + ", phonenumber=" + phonenumber + ", licenceplate=" + licenceplate + ", modeltype="
				+ modeltype + ", factoryname=" + factoryname + ", expiringdate=" + expiringdate + ", violationdate="
				+ violationdate + ", createtime=" + createtime + ", violationaddress=" + violationaddress
				+ ", violationcontent=" + violationcontent + ", punishmentways=" + punishmentways + "]";
	}
    
}
