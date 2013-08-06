package com.chenjb.struts;

import com.chenjb.domain.User;

/**
 * 注入User
 * 
 * @author ChenJianbin
 * 
 */
public interface UserAware {
	public void setUser(User user);
}
