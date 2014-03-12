package com.gm.infobus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.gm.infobus.entity.SearchCritera;
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
	public List<DBObject> getValueByParam(String collectionName, String[] params) {
		Query query = new Query();
		query.fields().exclude("_id");
		if (params != null) {
			for (String param : params) {
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
	public void batchInsertIntoCollection(List<DBObject> beans, String colName) {
		dataDAO.batchInsert(beans, colName);
	}

	@Override
	public void dropCollection(String collectionName) {
		dataDAO.clearCollection(collectionName);
	}

	@Override
	public List<DBObject> getDBObjects(String collectionName, SearchCritera critera) {
		Query query = new Query();
		Criteria c = Criteria.where("vin_2_9").is(critera.getVin2_9()).and("vin_10_17").is(critera.getVin10_17());
		if (!"".equals(critera.getDate())) {
			c.and("uploadTime").gte(critera.getDateTime().getMillis()).lt(critera.getDateTime().getMillis() + 24 * 60 * 60 * 1000);
		}
		if(!"all".equals(critera.getType())){
			c.and("type").is(critera.getType());
		}
		if(!"".equals(critera.getInterval())){
			c.and("interval").is(critera.getInterval());
		}
		query.addCriteria(c);
		return dataDAO.find(query, collectionName);
	}

	@Override
	public DBObject getNGIRecordById(String id, String collectionName) {
		Query query = new Query();
		Criteria c = Criteria.where("_id").is(id);
		query.addCriteria(c);
		return dataDAO.findOne(query, collectionName);
	}

}
