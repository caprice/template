package com.gm.infobus.service;

import java.util.List;

import com.gm.infobus.entity.SearchCritera;
import com.mongodb.DBObject;

public interface NGIDataService {
	void uploadNGIData(DBObject dbObject, String collectionName);
	List<DBObject> getDBObjects(String collectionName);
	List<DBObject> getDBObjects(String collectionName, SearchCritera critera);
	DBObject getNGIRecordById(String id, String collectionName);
	List<DBObject> getValueByParam(String collectionName, String[] params);
	DBObject addIntoCollection(String collectionName, DBObject bean);
	void dropCollection(String collectionName);
	void batchInsertIntoCollection(List<DBObject> beans, String colName);
}
