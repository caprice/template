package com.gm.infobus.repository;

import java.util.List;

import com.gm.infobus.entity.TripTrack;
import com.gm.infobus.mapper.BaseMapper;

public interface TripTrackDAO extends BaseMapper<TripTrack, Integer> {
	List<TripTrack> getTripTrackByTripId(int tripId);
}
