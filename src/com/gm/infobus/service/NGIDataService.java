package com.gm.infobus.service;

import java.util.List;

import com.gm.infobus.entity.SearchCritera;
import com.gm.infobus.repository.base.Pagination;
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
	List<DBObject> getLogDBObjects(String collectionName, SearchCritera critera);
	void clearLogsByDevice(String collectionName, SearchCritera critera);
	void clearNGIDataByVin(String collectionName, SearchCritera critera);
	Pagination<DBObject> getDBObjectsByPage(String collectionName, SearchCritera critera);
	Pagination<DBObject> getLogDBObjectsByPage(String collectionName, SearchCritera critera);
	
}
