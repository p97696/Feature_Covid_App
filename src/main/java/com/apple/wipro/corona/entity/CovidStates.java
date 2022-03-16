package com.apple.wipro.corona.entity;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.stereotype.Component;



@Component
public class CovidStates implements Serializable {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String city;
	private String province;
	private String country;
	private Date lastUpdate;
	private String keyId;
	private String confirmed;
	private String deaths;
	private String recovered;
	
	
	public CovidStates() {
		super();
	}
	public CovidStates(String city, String province, String country, Date lastUpdate, String keyId, String confirmed,
			String deaths, String recovered) {
		super();
		this.city = city;
		this.province = province;
		this.country = country;
		this.lastUpdate = lastUpdate;
		this.keyId = keyId;
		this.confirmed = confirmed;
		this.deaths = deaths;
		this.recovered = recovered;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
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
	@Override
	public String toString() {
		return "CovidStates [city=" + city + ", province=" + province + ", country=" + country + ", lastUpdate="
				+ lastUpdate + ", keyId=" + keyId + ", confirmed=" + confirmed + ", deaths=" + deaths + ", recovered="
				+ recovered + "]";
	}
	
	
	
	
	                

}
