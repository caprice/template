package com.gm.infobus.repository.DAO;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.gm.infobus.repository.BaseConfigurtionTest;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class MongoTest extends BaseConfigurtionTest {

	@Autowired
	protected MongoTemplate mongoTemplate;

	@Test
	public void testInsert() {
		DBObject o = (DBObject)JSON.parse("{name:123,age:23}");
		mongoTemplate.insert(o, "test");
	}
}
