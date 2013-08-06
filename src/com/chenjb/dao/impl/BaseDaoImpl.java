package com.chenjb.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.chenjb.dao.BaseDao;

/**
 * 抽象dao实现,专门用于继承的
 * 
 * @author ChenJianbin
 * @param <T>
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	private Class clazz;

	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0];
	}

	// 注入sessionFactory(这样就不需要用HibernateTemplate模板了)
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public void saveEntity(T t) {
		sessionFactory.getCurrentSession().save(t);
	}

	@Override
	public void updateEntity(T t) {
		sessionFactory.getCurrentSession().update(t);
	}

	@Override
	public void deleteEntity(T t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void batchEntityByHQL(String hql, Object... objects) {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		q.executeUpdate();
	}

	@Override
	public T getEntity(Integer id) {
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}

	@Override
	public T loadEntity(Integer id) {
		return (T) sessionFactory.getCurrentSession().load(clazz, id);
	}

	@Override
	public List<T> findEntityByHQL(String hql, Object... objects) {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		return q.list();
	}

	// 单值检索,确保查询结果有且仅有一条记录
	@Override
	public Object uniqueResult(String hql, Object... objects) {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		return q.uniqueResult();
	}
}
