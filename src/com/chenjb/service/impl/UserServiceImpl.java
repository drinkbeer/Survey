package com.chenjb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chenjb.dao.BaseDao;
import com.chenjb.domain.User;
import com.chenjb.service.UserService;
import com.chenjb.util.ValidateUtil;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {
	// 覆盖BaseServiceImpl<User>
	@Override
	@Resource(name = "userDao")
	public void setBaseDao(BaseDao<User> baseDao) {
		super.setBaseDao(baseDao);
	}

	// 验证email的有效性
	@Override
	public boolean isRegisted(String email) {
		String hql = "from User u where u.email=?";
		List<User> list = this.findEntityByHQL(hql, email);
		return ValidateUtil.isValid(list);
	}

	//校验登陆
	@Override
	public User loginValidate(String email, String password) {
		String hql = "from User u where u.email=? and u.password=?";
		List<User> list = this.findEntityByHQL(hql, email, password);
		return ValidateUtil.isValid(list) ? list.get(0) : null;
	}
}
