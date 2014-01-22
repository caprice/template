package com.gm.infobus.util;

import java.util.List;

import com.github.davidmoten.geo.GeoHash;
import com.github.davidmoten.geo.LatLong;
import com.gm.infobus.entity.Point;

public class GEOUtils {

	private static final double EARTH_RADIUS = 6378137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
	 * 
	 * @param lng1
	 * @param lat1
	 * @param lng2
	 * @param lat2
	 * @return
	 */
	public static double getDistance(Point p1, Point p2) {
		double radLat1 = rad(p1.getLatitude());
		double radLat2 = rad(p2.getLatitude());
		double a = radLat1 - radLat2;
		double b = rad(p1.getLongitude()) - rad(p2.getLongitude());
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	public static void main(String[] a) {
		String geoHash = GeoHash.encodeHash(31.2566, 121.612);
		System.out.println(geoHash);
		List<String> neighbours = GeoHash.neighbours(geoHash);
		for(String str : neighbours){
			System.out.println(str);
		}
	}
}
