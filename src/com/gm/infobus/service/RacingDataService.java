package com.gm.infobus.service;

import java.util.List;

import com.gm.infobus.entity.Point;
import com.gm.infobus.entity.Trip;
import com.gm.infobus.entity.TripTrack;

public interface RacingDataService {
	
	void batchInsertTripTrackData(List<TripTrack> tripTrackList);
	void addTrip(Trip trip);
	List<Trip> getNeighborTrips(Point p);
}
