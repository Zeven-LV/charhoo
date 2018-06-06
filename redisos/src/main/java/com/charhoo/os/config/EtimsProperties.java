package com.charhoo.os.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.etims")
@Component
public class EtimsProperties {

	private String apiHost = "";
	private String billHost = "";
	private String dakaTokenHost = "";
	private String zyptVehicleVCount = "";
	private String redisOnlineInterval = "";
	private String innetVerifyDay = "";
	private String innetVerifyTotal = "";
	private String innetVerifyStatus = "";
	private String innetVerifyCount = "";
	private String ownerVerifyDay = "";
    private String ownerVerifyTotal = "";
    private String ownerVerifyStatus = "";
    private String ownerVerifyCount = "";
    private String locationVerifyDay = "";
	private String locationVerifyTotal = "";
    private String locationVerifyStatus = "";
    private String locationVerifyCount = "";
	private String licenceVerifyDay = "";
    private String licenceVerifyTotal = "";
    private String licenceVerifyStatus = "";
    private String licenceVerifyCount = "";
    private String limitMobile = "";

	public void setDakaTokenHost(String dakaTokenHost) {
		this.dakaTokenHost = dakaTokenHost;
	}

	public String getDakaTokenHost() {
		return dakaTokenHost;
	}

	public String getInnetVerifyDay() {
		return innetVerifyDay;
	}

	public void setInnetVerifyDay(String innetVerifyDay) {
		this.innetVerifyDay = innetVerifyDay;
	}

	public String getInnetVerifyTotal() {
		return innetVerifyTotal;
	}

	public void setInnetVerifyTotal(String innetVerifyTotal) {
		this.innetVerifyTotal = innetVerifyTotal;
	}

	public String getInnetVerifyStatus() {
		return innetVerifyStatus;
	}

	public void setInnetVerifyStatus(String innetVerifyStatus) {
		this.innetVerifyStatus = innetVerifyStatus;
	}

	public String getInnetVerifyCount() {
		return innetVerifyCount;
	}

	public void setInnetVerifyCount(String innetVerifyCount) {
		this.innetVerifyCount = innetVerifyCount;
	}

	public String getOwnerVerifyDay() {
		return ownerVerifyDay;
	}

	public void setOwnerVerifyDay(String ownerVerifyDay) {
		this.ownerVerifyDay = ownerVerifyDay;
	}

	public String getOwnerVerifyTotal() {
		return ownerVerifyTotal;
	}

	public void setOwnerVerifyTotal(String ownerVerifyTotal) {
		this.ownerVerifyTotal = ownerVerifyTotal;
	}

	public String getOwnerVerifyStatus() {
		return ownerVerifyStatus;
	}

	public void setOwnerVerifyStatus(String ownerVerifyStatus) {
		this.ownerVerifyStatus = ownerVerifyStatus;
	}

	public String getOwnerVerifyCount() {
		return ownerVerifyCount;
	}

	public void setOwnerVerifyCount(String ownerVerifyCount) {
		this.ownerVerifyCount = ownerVerifyCount;
	}

	public String getLocationVerifyDay() {
		return locationVerifyDay;
	}

	public void setLocationVerifyDay(String locationVerifyDay) {
		this.locationVerifyDay = locationVerifyDay;
	}

	public String getLocationVerifyTotal() {
		return locationVerifyTotal;
	}

	public void setLocationVerifyTotal(String locationVerifyTotal) {
		this.locationVerifyTotal = locationVerifyTotal;
	}

	public String getLocationVerifyStatus() {
		return locationVerifyStatus;
	}

	public void setLocationVerifyStatus(String locationVerifyStatus) {
		this.locationVerifyStatus = locationVerifyStatus;
	}

	public String getLocationVerifyCount() {
		return locationVerifyCount;
	}

	public void setLocationVerifyCount(String locationVerifyCount) {
		this.locationVerifyCount = locationVerifyCount;
	}

	public String getLicenceVerifyDay() {
		return licenceVerifyDay;
	}

	public void setLicenceVerifyDay(String licenceVerifyDay) {
		this.licenceVerifyDay = licenceVerifyDay;
	}

	public String getLicenceVerifyTotal() {
		return licenceVerifyTotal;
	}

	public void setLicenceVerifyTotal(String licenceVerifyTotal) {
		this.licenceVerifyTotal = licenceVerifyTotal;
	}

	public String getLicenceVerifyStatus() {
		return licenceVerifyStatus;
	}

	public void setLicenceVerifyStatus(String licenceVerifyStatus) {
		this.licenceVerifyStatus = licenceVerifyStatus;
	}

	public String getLicenceVerifyCount() {
		return licenceVerifyCount;
	}

	public void setLicenceVerifyCount(String licenceVerifyCount) {
		this.licenceVerifyCount = licenceVerifyCount;
	}
    
    public String getLimitMobile() {
		return limitMobile;
	}

	public void setLimitMobile(String limitMobile) {
		this.limitMobile = limitMobile;
	}

	public String getApiHost() {
		return apiHost;
	}

	public void setApiHost(String apiHost) {
		this.apiHost = apiHost;
	}

	public String getBillHost() {
		return billHost;
	}

	public void setBillHost(String billHost) {
		this.billHost = billHost;
	}

	public String getZyptVehicleVCount() {
		return zyptVehicleVCount;
	}

	public void setZyptVehicleVCount(String zyptVehicleVCount) {
		this.zyptVehicleVCount = zyptVehicleVCount;
	}

	public String getRedisOnlineInterval() {
		return redisOnlineInterval;
	}

	public void setRedisOnlineInterval(String redisOnlineInterval) {
		this.redisOnlineInterval = redisOnlineInterval;
	}


}