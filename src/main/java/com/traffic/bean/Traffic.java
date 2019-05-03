package com.traffic.bean;

import java.util.List;

/**
 * 
 */
public class Traffic {
    /**
     * 编号
     */
    private Integer id;

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
    
    private List<Punishmentway> punishmentways;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
        this.address = address == null ? null : address.trim();
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
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    public String getLicenceplate() {
        return licenceplate;
    }

    public void setLicenceplate(String licenceplate) {
        this.licenceplate = licenceplate == null ? null : licenceplate.trim();
    }

    public String getModeltype() {
        return modeltype;
    }

    public void setModeltype(String modeltype) {
        this.modeltype = modeltype == null ? null : modeltype.trim();
    }

    public String getFactoryname() {
        return factoryname;
    }

    public void setFactoryname(String factoryname) {
        this.factoryname = factoryname == null ? null : factoryname.trim();
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
        this.violationaddress = violationaddress == null ? null : violationaddress.trim();
    }

    public String getViolationcontent() {
        return violationcontent;
    }

    public void setViolationcontent(String violationcontent) {
        this.violationcontent = violationcontent == null ? null : violationcontent.trim();
    }

	public List<Punishmentway> getPunishmentways() {
		return punishmentways;
	}

	public void setPunishmentways(List<Punishmentway> punishmentways) {
		this.punishmentways = punishmentways;
	}
}