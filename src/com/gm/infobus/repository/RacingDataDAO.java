package com.gm.infobus.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gm.infobus.entity.Trip;

@Repository
public class RacingDataDAO {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void addTrip(Trip trip) {
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		try {
			sqlSession.insert("com.gm.infobus.repository.TripDAO", trip);
			Map<String, Object> map = new HashMap<String, Object>();
			if (trip.getTrackList() != null) {
				map.put("tripTrackList", trip.getTrackList());
				sqlSession.insert("com.gm.infobus.repository.TripTrackDAO.batchInsert", map);
			}
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
}
