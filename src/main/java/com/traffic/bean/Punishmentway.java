package com.traffic.bean;

/**
 * 
 * 
 * @date 2019-04-30
 */
public class Punishmentway {
    private Integer id;

    /**
     * 违章信息id
     */
    private Integer trafficid;

    /**
     * 违章类型 1 警告 2 罚款 3 暂扣驾驶执照
     */
    private Integer punishmenttype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrafficid() {
        return trafficid;
    }

    public void setTrafficid(Integer trafficid) {
        this.trafficid = trafficid;
    }

    public Integer getPunishmenttype() {
        return punishmenttype;
    }

    public void setPunishmenttype(Integer punishmenttype) {
        this.punishmenttype = punishmenttype;
    }
}