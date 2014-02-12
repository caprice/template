package com.gm.infobus.service;

import java.util.List;
import java.util.Map;

import com.gm.infobus.entity.SearchCritera;
import com.mongodb.DBObject;

public interface NGIDataService {
	
	void uploadNGIData(DBObject dbObject, String collectionName);
	List<DBObject> getDBObjects(String collectionName);
	List<DBObject> getDBObjects(String collectionName, SearchCritera critera);
	DBObject getNGIRecordById(SearchCritera critera, String collectionName);
	List<DBObject> getValueByParam(String collectionName, String[] params);
	DBObject addIntoCollection(String collectionName, DBObject bean);
	void dropCollection(String collectionName);
}
