package com.gm.infobus.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.davidmoten.geo.GeoHash;
import com.gm.infobus.entity.Point;
import com.gm.infobus.entity.Trip;
import com.gm.infobus.entity.TripTrack;
import com.gm.infobus.repository.TripDAO;
import com.gm.infobus.repository.TripTrackDAO;
import com.gm.infobus.service.RacingDataService;
import com.gm.infobus.util.GEOUtils;

@Transactional
@Service
public class RacingDataServiceImpl implements RacingDataService {

	@Autowired
	private TripDAO tripDAO;
	@Autowired
	private TripTrackDAO tripTrackDAO;

	@Override
	public void addTrip(Trip trip) {
		trip.setGeohashcode(GeoHash.encodeHash(trip.getLatitude(), trip.getLongitude()));
		tripDAO.insert(trip);
		int id = trip.getId();
		if (trip.getTrackList() != null) {
			for (TripTrack track : trip.getTrackList()) {
				track.setTripid(id);
				tripTrackDAO.insert(track);
			}
		}
	}

	@Override
	public void batchInsertTripTrackData(List<TripTrack> tripTrackList) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Trip> getNeighborTrips(final Point p) {
		String geohashcode = GeoHash.encodeHash(p.getLatitude(), p.getLongitude());
		// 5位的编码能表示10平方千米范围的矩形区域，而6位编码能表示更精细的区域（约0.34平方千米）
		String likeGeoHash = geohashcode.substring(0, 5);
		Map<String, String> map = new HashMap<String, String>();
		map.put("likeGeohash", likeGeoHash);
		List<Trip> trips = tripDAO.getNeighborTrips(map);

		if (trips != null) {
			for (Trip trip : trips) {
				trip.setTrackList(tripTrackDAO.getTripTrackByTripId(trip.getId()));
				Point trip_p = new Point(trip.getLatitude(), trip.getLongitude());
				trip.setDistance(GEOUtils.getDistance(p, trip_p));
			}
		}

		Collections.sort(trips, new Comparator<Trip>() {
			@Override
			public int compare(Trip o1, Trip o2) {
				if (o1.getDistance() < o2.getDistance()) {
					return -1;
				} else if (o1.getDistance() < o2.getDistance()) {
					return 1;
				}
				return 0;
			}

		});
		return trips;
	}

}
