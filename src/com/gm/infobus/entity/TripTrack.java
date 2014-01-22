package com.gm.infobus.entity;

import com.gm.infobus.repository.base.AbstractDO;

/**
 * @Description:
 * @author liuwei
 * @date 2014年1月16日 下午2:06:25
 * 
 */
public class TripTrack extends AbstractDO {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 下午2:27:07
	 */
	private static final long serialVersionUID = 5240231400011024628L;
	private int id;
	private int tripid;
	private double latitude;
	private double longitude;
	private double altitude;
	private double distance;
	private double speed;
	private String routetitle;
	private long recordtime;

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final int getTripid() {
		return tripid;
	}

	public final void setTripid(int tripid) {
		this.tripid = tripid;
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

	public final double getAltitude() {
		return altitude;
	}

	public final void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public final double getDistance() {
		return distance;
	}

	public final void setDistance(double distince) {
		this.distance = distince;
	}

	public final double getSpeed() {
		return speed;
	}

	public final void setSpeed(double speed) {
		this.speed = speed;
	}

	public final String getRoutetitle() {
		return routetitle;
	}

	public final void setRoutetitle(String routetitle) {
		this.routetitle = routetitle;
	}

	public final long getRecordtime() {
		return recordtime;
	}

	public final void setRecordtime(long recordtime) {
		this.recordtime = recordtime;
	}

}
