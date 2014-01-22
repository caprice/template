package com.gm.infobus.repository.service;

import java.util.ArrayList;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gm.infobus.entity.Trip;
import com.gm.infobus.entity.TripTrack;
import com.gm.infobus.repository.BaseConfigurtionTest;
import com.gm.infobus.service.RacingDataService;

public class TripTackServiceTest extends BaseConfigurtionTest {
	@Autowired
	private RacingDataService service;

	@Test
	public void testAddTrip() {
		Trip trip = new Trip();
		trip.setMapurl("www.baidu11.com");
		trip.setUserName("sprin02");
		ArrayList<TripTrack> list = new ArrayList<TripTrack>();
		for (int i = 0; i < 5; i++) {
			TripTrack a = new TripTrack();
			a.setSpeed(121 + i);
			list.add(a);
		}
		trip.setTrackList(list);
		service.addTrip(trip);
		System.out.println("成功往数据库中插入一条数据");
	}

}
