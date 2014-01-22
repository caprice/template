package com.gm.infobus.mapper;

import java.util.List;

import com.gm.infobus.repository.base.AbstractDO;


/**
* @Description: 
* @author liuwei
* @date 2014年1月16日 下午12:32:21
*
* @param <T>
* @param <PK>
*/
public interface BaseMapper<T extends AbstractDO, PK extends java.io.Serializable> {

	PK insert(T model);

	void delete(PK modelPK);
	
	T load(PK modelPK);
	
	void update(T model);

	void updateSelective(T model);
	
	int countAll();
	
	List<T> findAll();
	
	List<PK> findAllIds();
	
}
