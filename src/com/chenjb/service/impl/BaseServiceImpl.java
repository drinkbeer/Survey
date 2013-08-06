package com.chenjb.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import com.chenjb.dao.BaseDao;
import com.chenjb.service.BaseService;

@SuppressWarnings("unchecked")
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	private BaseDao<T> baseDao;
	private Class<T> clazz;

	public BaseServiceImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	@Resource
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void saveEntity(T t) {
		baseDao.saveEntity(t);
	}

	@Override
	public void updateEntity(T t) {
		baseDao.updateEntity(t);
	}

	@Override
	public void deleteEntity(T t) {
		baseDao.deleteEntity(t);
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		baseDao.saveOrUpdateEntity(t);
	}

	@Override
	public void batchEntityByHQL(String hql, Object... objects) {
		baseDao.batchEntityByHQL(hql, objects);
	}

	@Override
	public T getEntity(Integer id) {
		return baseDao.getEntity(id);
	}

	@Override
	public T loadEntity(Integer id) {
		return baseDao.getEntity(id);
	}

	@Override
	public List<T> findEntityByHQL(String hql, Object... objects) {
		return baseDao.findEntityByHQL(hql, objects);
	}

	// 返回唯一的查询结果
	public Object uniqueResult(String hql, Object... objects) {
		return baseDao.uniqueResult(hql, objects);
	}

	/**
	 * 查询所有的对象
	 */
	public List<T> findAllEntities() {
		String hql = "from " + clazz.getCanonicalName();
		return this.findEntityByHQL(hql);
	}
}
