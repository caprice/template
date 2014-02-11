package com.gm.infobus.service;

import java.util.List;

import com.mongodb.DBObject;

public interface NGIDataService {
	
	void uploadNGIData(DBObject dbObject, String collectionName);
	List<DBObject> getDBObjects(String collectionName);
	DBObject getNGIRecordById(String id, String collectionName);
	List<DBObject> getValueByParam(String collectionName, String[] params);
	DBObject addIntoCollection(String collectionName, DBObject bean);
	void dropCollection(String collectionName);
}
