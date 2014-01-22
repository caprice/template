package com.gm.infobus.repository.DAO;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gm.infobus.entity.TripTrack;
import com.gm.infobus.repository.BaseConfigurtionTest;
import com.gm.infobus.repository.TripTrackDAO;

public class TripTrackDAOTest extends BaseConfigurtionTest {
	@Autowired
	private TripTrackDAO tripTrackDao;

	@Test
	public void testAddTrip() {
		TripTrack tripTrack = new TripTrack();
		tripTrack.setRoutetitle("新年新气象");
		tripTrack.setSpeed(12121.21);
		tripTrack.setTripid(123);
		// user.setAccountName("liumeng1");
		// user.setPlate("沪MB2342");
		// user.setNickName("小鬼");
		tripTrackDao.insert(tripTrack);
		System.out.println("成功往数据库中插入一条数据");
	}

	@Test
	public void testFindAllTrips() {
		List<TripTrack> list = tripTrackDao.findAll();
		if (list != null && !list.isEmpty()) {
			System.out.println("所有用户数目" + list.size());
		}
		System.out.println("user name:" + list.get(0).getRoutetitle());

	}
}
