package com.chenjb.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chenjb.domain.User;
import com.chenjb.service.UserService;
import com.chenjb.util.DataUtil;

public class TestUserService {
	private static ApplicationContext ac;

	@BeforeClass
	public static void initAC() {
		ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	}

	@Test
	public void testUser() {
		UserService userService = (UserService) ac.getBean("userService");
		User u = new User();
		u.setEmail("chen.jb@tonkpay.com");
		u.setPassword(DataUtil.md5("chen.jb@tonkpay.com"));
		u.setNickName("stone");
		userService.saveEntity(u);
	}
}
