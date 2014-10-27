package com.gm.infobus.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.gm.infobus.repository.base.Pagination;

public abstract class MongodbBaseDAO<T> {

	/**
	 * spring mongodb　集成操作类　
	 */
	@Autowired
	protected MongoTemplate mongoTemplate;

	// 每页显示五条
	protected static final int PAGE_SIZE = 8;

	/**
	 * 通过条件查询,查询分页结果
	 */
	public Pagination<T> getPage(int currentPage, Query query, String collectionName) {
		// 获取总条数
		long totalCount = this.mongoTemplate.count(query, collectionName);

		Pagination<T> page = new Pagination<T>(currentPage, (int) totalCount);
		int skip = (currentPage - 1) * page.getPageSize();
		query.skip(skip);// skip相当于从那条记录开始
		query.limit(page.getPageSize());// 从skip开始,取多少条记录
		List<T> datas = this.find(query, collectionName);
		page.setItems(datas);// 获取数据
		return page;
	}

	/**
	 * 通过条件查询实体(集合)
	 * 
	 * @param query
	 */
	public List<T> find(Query query) {
		return mongoTemplate.find(query, this.getEntityClass());
	}

	/**
	 * 通过条件查询实体(集合)
	 * 
	 * @param query
	 */
	public List<T> find(Query query, String collectionName) {
		return mongoTemplate.find(query, this.getEntityClass(), collectionName);
	}

	/**
	 * 通过条件查询实体(集合)
	 * 
	 * @param query
	 */
	public List<T> findAll(String collectionName) {
		return mongoTemplate.findAll(this.getEntityClass(), collectionName);
	}

	/**
	 * 通过一定的条件查询一个实体
	 * 
	 * @param query
	 * @return
	 */
	public T findOne(Query query) {
		return mongoTemplate.findOne(query, this.getEntityClass());
	}

	/**
	 * 通过一定的条件查询一个实体
	 * 
	 * @param query
	 * @return
	 */
	public T findOne(Query query, String collectionName) {
		return mongoTemplate.findOne(query, this.getEntityClass(), collectionName);
	}

	/**
	 * 通过条件查询更新数据
	 * 
	 * @param query
	 * @param update
	 * @return
	 */
	public void update(Query query, Update update) {
		mongoTemplate.upsert(query, update, this.getEntityClass());
	}

	/**
	 * 保存一个对象到mongodb
	 * 
	 * @param bean
	 * @return
	 */
	public T save(T bean) {
		mongoTemplate.save(bean);
		return bean;
	}

	/**
	 * 保存一个对象到mongodb
	 * 
	 * @param bean
	 * @return
	 */
	public T save(T bean, String collectionName) {
		mongoTemplate.save(bean, collectionName);
		return bean;
	}

	/**
	 * 
	 * @param bean
	 * @return
	 */
	public void batchInsert(List<T> list, String collectionName) {
		mongoTemplate.insert(list, collectionName);
	}

	/**
	 * 保存一个对象到mongodb
	 * 
	 * @param bean
	 * @return
	 */
	public void clearCollection(String collectionName) {
		mongoTemplate.dropCollection(collectionName);
	}

	/**
	 * 通过ID获取记录
	 * 
	 * @param id
	 * @return
	 */
	public T get(String id) {
		return mongoTemplate.findById(id, this.getEntityClass());
	}

	/**
	 * 通过ID获取记录,并且指定了集合名(表的意思)
	 * 
	 * @param id
	 * @param collectionName
	 *            集合名
	 * @return
	 */
	public T get(String id, String collectionName) {
		return mongoTemplate.findById(id, this.getEntityClass(), collectionName);
	}

	/**
	 * 通过ID获取记录,并且指定了集合名(表的意思)
	 * 
	 * @param id
	 * @param collectionName
	 *            集合名
	 * @return
	 */
	public void remove(Query query, String collectionName) {
		mongoTemplate.remove(query, collectionName);
	}

	/**
	 * 获取需要操作的实体类class
	 * 
	 * @return
	 */
	protected abstract Class<T> getEntityClass();

}