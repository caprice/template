package com.gm.infobus.repository.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gm.infobus.entity.Trip;
import com.gm.infobus.entity.TripTrack;
import com.gm.infobus.repository.BaseConfigurtionTest;
import com.gm.infobus.repository.RacingDataDAO;
import com.gm.infobus.repository.TripDAO;

public class TripDAOTest extends BaseConfigurtionTest {
	@Autowired
	private RacingDataDAO racingDao;
	@Autowired
	private TripDAO tripDAO;

	@Test
	public void testAddTrip() {
		Trip trip = new Trip();
		trip.setMapurl("www.baidu.com");
		trip.setUserName("sprin01");
		// user.setAccountName("liumeng1");
		// user.setPlate("沪MB2342");
		// user.setNickName("小鬼");
		ArrayList<TripTrack> list = new ArrayList<TripTrack>();
		for (int i = 0; i < 50; i++) {
			TripTrack a = new TripTrack();
			a.setTripid(12 + i);
			a.setSpeed(121 + i);
			list.add(a);
		}
		trip.setTrackList(list);
		racingDao.addTrip(trip);
		System.out.println("成功往数据库中插入一条数据");
	}

	@Test
	public void testFindAllTrips() {
		String likeGeoHash = "wcyp2";
		Map<String, String> map = new HashMap<String, String>();
		map.put("likeGeohash", likeGeoHash);
		List<Trip> list = tripDAO.getNeighborTrips(map);
		if (list != null && !list.isEmpty()) {
			System.out.println("所有数目" + list.size());
		}

	}
}
