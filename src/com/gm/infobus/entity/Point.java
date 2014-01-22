package com.gm.infobus.entity;

public class Point {

	private double latitude;
	private double longitude;

	
	public Point(){
		
	}
	public Point(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public final double getLatitude() {
		return latitude;
	}

	public final void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public final double getLongitude() {
		return longitude;
	}

	public final void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
