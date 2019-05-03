package com.traffic.bean.ana;

import java.util.List;

import com.traffic.bean.Punishmentway;
import com.traffic.bean.Traffic;

public class TrafficJsonObj {

	private Traffic traffic;
	
	private List<Punishmentway> punishmentways;

	public Traffic getTraffic() {
		return traffic;
	}

	public void setTraffic(Traffic traffic) {
		this.traffic = traffic;
	}

	public List<Punishmentway> getPunishmentways() {
		return punishmentways;
	}

	public void setPunishmentways(List<Punishmentway> punishmentways) {
		this.punishmentways = punishmentways;
	}
}
