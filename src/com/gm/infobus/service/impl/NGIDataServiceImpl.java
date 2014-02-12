package com.gm.infobus.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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

	@Override
	public List<DBObject> getDBObjects(String collectionName, SearchCritera critera) {
		Query query = new Query();  
		Criteria c = Criteria.where("vin_2_9").is(StringUtils.isNumeric(critera.getVin2_9())? Integer.valueOf(critera.getVin2_9()):critera.getVin2_9())
				.and("vin_10_17").is(StringUtils.isNumeric(critera.getVin10_17())? Integer.valueOf(critera.getVin10_17()):critera.getVin10_17());
		if(!"".equals(critera.getDate())){
			c.and("year").is(critera.getDateTime().getYear())
			.and("month").is(critera.getDateTime().getMonthOfYear())
			.and("day").is(critera.getDateTime().getDayOfMonth());
		}
		query.addCriteria(c);
		return dataDAO.find(query, collectionName);
	}

	@Override
	public DBObject getNGIRecordById(SearchCritera critera, String collectionName) {
		Query query = new Query();  
		Criteria c = Criteria.where("vin_2_9").is(StringUtils.isNumeric(critera.getVin_2_9())? Integer.valueOf(critera.getVin_2_9()):critera.getVin_2_9())
				.and("vin_10_17").is(StringUtils.isNumeric(critera.getVin_10_17())? Integer.valueOf(critera.getVin_10_17()):critera.getVin_10_17());
			c.and("year").is(Integer.valueOf(critera.getYear()))
			.and("month").is(Integer.valueOf(critera.getMonth()))
			.and("day").is(Integer.valueOf(critera.getDay()))
			.and("hours").is(Integer.valueOf(critera.getHours()))
			.and("minutes").is(Integer.valueOf(critera.getMinutes()))
			.and("seconds").is(Integer.valueOf(critera.getSeconds()));
		query.addCriteria(c);
		return dataDAO.findOne(query, collectionName);
	}

}
