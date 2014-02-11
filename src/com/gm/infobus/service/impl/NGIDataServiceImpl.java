package com.gm.infobus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.gm.infobus.repository.NGIDataDAO;
import com.gm.infobus.service.NGIDataService;
import com.mongodb.DBObject;

@Service
public class NGIDataServiceImpl implements NGIDataService {
	
	@Autowired
	private NGIDataDAO dataDAO;

	@Override
	public void uploadNGIData(DBObject dbObject, String collectionName) {
		dataDAO.save(dbObject, collectionName);
	}

	@Override
	public List<DBObject> getDBObjects(String collectionName) {
		Query query = new Query();  
		query.fields().exclude("_class");
		return dataDAO.find(query, collectionName);
	}

	@Override
	public DBObject getNGIRecordById(String id, String collectionName) {
		
		return dataDAO.get(id, collectionName);
	}

	@Override
	public List<DBObject> getValueByParam(String collectionName, String[] params) {
		Query query = new Query();  
		query.fields().exclude("_id");
		if(params != null){
			for(String param : params){
				query.fields().include(param);
			}
		}
		return dataDAO.find(query, collectionName);
	}

	@Override
	public DBObject addIntoCollection(String colName, DBObject bean) {
		return dataDAO.save(bean, colName);
	}

	@Override
	public void dropCollection(String collectionName) {
		dataDAO.clearCollection(collectionName);
	}

}
