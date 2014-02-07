package com.gm.infobus.repository;

import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;

@Repository
public class NGIDataDAO extends MongodbBaseDAO<DBObject> {

	@Override
	protected Class<DBObject> getEntityClass() {
		return DBObject.class;
	}

}
