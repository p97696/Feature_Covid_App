package com.apple.wipro.corona.entity;

import java.io.Serializable;

public class CovidTotal implements Serializable {
	
	private String confirmed;
	private String deaths;
	private String recovered;
	private String lastChecked;
	private String lastReported;
	private String location;
	
	
	public CovidTotal() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CovidTotal(String confirmed, String deaths, String recovered, String lastChecked, String lastReported,
			String location) {
		super();
		this.confirmed = confirmed;
		this.deaths = deaths;
		this.recovered = recovered;
		this.lastChecked = lastChecked;
		this.lastReported = lastReported;
		this.location = location;
	}
	public String getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}
	public String getDeaths() {
		return deaths;
	}
	public void setDeaths(String deaths) {
		this.deaths = deaths;
	}
	public String getRecovered() {
		return recovered;
	}
	public void setRecovered(String recovered) {
		this.recovered = recovered;
	}
	public String getLastChecked() {
		return lastChecked;
	}
	public void setLastChecked(String lastChecked) {
		this.lastChecked = lastChecked;
	}
	public String getLastReported() {
		return lastReported;
	}
	public void setLastReported(String lastReported) {
		this.lastReported = lastReported;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "CovidTotal [confirmed=" + confirmed + ", deaths=" + deaths + ", recovered=" + recovered
				+ ", lastChecked=" + lastChecked + ", lastReported=" + lastReported + ", location=" + location + "]";
	}
	
	

}
