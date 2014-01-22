package com.gm.infobus.entity;

import java.util.List;

import com.gm.infobus.repository.base.AbstractDO;

/**
 * @Description:
 * @author liuwei
 * @date 2014年1月16日 下午2:06:25
 * 
 */
public class Trip extends AbstractDO {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 下午2:05:42
	 */
	private static final long serialVersionUID = 96312286532732735L;
	private int id;
	private String userName;
	private double latitude;
	private double longitude;
	private String title;
	private String mapurl;
	private long recordtime;
	private String geohashcode;
	private double distance;
	private List<TripTrack> trackList;

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getUserName() {
		return userName;
	}

	public final void setUserName(String userName) {
		this.userName = userName;
	}

	public final String getTitle() {
		return title;
	}

	public final void setTitle(String title) {
		this.title = title;
	}

	public final String getMapurl() {
		return mapurl;
	}

	public final void setMapurl(String mapurl) {
		this.mapurl = mapurl;
	}

	public final long getRecordtime() {
		return recordtime;
	}

	public final void setRecordtime(long recordtime) {
		this.recordtime = recordtime;
	}

	public final List<TripTrack> getTrackList() {
		return trackList;
	}

	public final void setTrackList(List<TripTrack> trackList) {
		this.trackList = trackList;
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

	public final String getGeohashcode() {
		return geohashcode;
	}

	public final void setGeohashcode(String geohashcode) {
		this.geohashcode = geohashcode;
	}

	public final double getDistance() {
		return distance;
	}

	public final void setDistance(double distance) {
		this.distance = distance;
	}
}
