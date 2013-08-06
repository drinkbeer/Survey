package com.chenjb.service;

import com.chenjb.domain.User;

public interface UserService extends BaseService<User> {

	//验证email有效性
	boolean isRegisted(String email);

	//校验登陆
	User loginValidate(String email, String md5);
}
