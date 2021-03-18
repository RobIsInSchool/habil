package com.radams.geoNames;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostalCodesItem{

	@JsonProperty("adminCode2")
	private String adminCode2;

	@JsonProperty("adminCode1")
	private String adminCode1;

	@JsonProperty("adminName2")
	private String adminName2;

	@JsonProperty("lng")
	private double lng;

	@JsonProperty("distance")
	private String distance;

	@JsonProperty("countryCode")
	private String countryCode;

	@JsonProperty("postalCode")
	private String postalCode;

	@JsonProperty("adminName1")
	private String adminName1;

	@JsonProperty("placeName")
	private String placeName;

	@JsonProperty("lat")
	private double lat;

	public void setAdminCode2(String adminCode2){
		this.adminCode2 = adminCode2;
	}

	public String getAdminCode2(){
		return adminCode2;
	}

	public void setAdminCode1(String adminCode1){
		this.adminCode1 = adminCode1;
	}

	public String getAdminCode1(){
		return adminCode1;
	}

	public void setAdminName2(String adminName2){
		this.adminName2 = adminName2;
	}

	public String getAdminName2(){
		return adminName2;
	}

	public void setLng(double lng){
		this.lng = lng;
	}

	public double getLng(){
		return lng;
	}

	public void setDistance(String distance){
		this.distance = distance;
	}

	public String getDistance(){
		return distance;
	}

	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public void setPostalCode(String postalCode){
		this.postalCode = postalCode;
	}

	public String getPostalCode(){
		return postalCode;
	}

	public void setAdminName1(String adminName1){
		this.adminName1 = adminName1;
	}

	public String getAdminName1(){
		return adminName1;
	}

	public void setPlaceName(String placeName){
		this.placeName = placeName;
	}

	public String getPlaceName(){
		return placeName;
	}

	public void setLat(double lat){
		this.lat = lat;
	}

	public double getLat(){
		return lat;
	}

	@Override
 	public String toString(){
		return 
			"PostalCodesItem{" + 
			"adminCode2 = '" + adminCode2 + '\'' + 
			",adminCode1 = '" + adminCode1 + '\'' + 
			",adminName2 = '" + adminName2 + '\'' + 
			",lng = '" + lng + '\'' + 
			",distance = '" + distance + '\'' + 
			",countryCode = '" + countryCode + '\'' + 
			",postalCode = '" + postalCode + '\'' + 
			",adminName1 = '" + adminName1 + '\'' + 
			",placeName = '" + placeName + '\'' + 
			",lat = '" + lat + '\'' + 
			"}";
		}
}