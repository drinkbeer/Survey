package com.chenjb.struts.action;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
/**
 * 抽象Action，用于把所有Action中的共同部分抽象出来重用
 * @author ChenJianbin
 * @param <T>
 */
@SuppressWarnings({"serial","rawtypes","unchecked"})
public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T>, Preparable {
	public T model;
	
	public BaseAction() {
		try {
			ParameterizedType type = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Class clazz = (Class) type.getActualTypeArguments()[0];
			model=(T) clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void prepare() throws Exception {
	}

	@Override
	public T getModel() {
		return model;
	}

}
