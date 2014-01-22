package com.gm.infobus.repository;

import java.util.List;
import java.util.Map;

import com.gm.infobus.entity.Trip;
import com.gm.infobus.mapper.BaseMapper;

public interface TripDAO extends BaseMapper<Trip, Integer>{

	List<Trip> getNeighborTrips(Map<String, String> map);

}
