package com.chenjb.struts.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chenjb.domain.User;
import com.chenjb.service.UserService;
import com.chenjb.util.DataUtil;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class LoginAction extends BaseAction<User> implements SessionAware {

	@Resource
	private UserService userService;

	private Map<String, Object> sessionMap;

	public User getModel() {
		return model;
	}

	// 达到登陆页面
	public String toLoginPage() {
		return "loginPage";
	}

	// 进行登陆
	public String doLogin() {
		return "success";
	}

	public void validateDoLogin() {
		ServletActionContext.getServletContext();
		User u = userService.loginValidate(model.getEmail(),
				DataUtil.md5(model.getPassword()));
		// 验证失败
		if (u == null) {
			this.addActionError("email或者password错误");
		}
		// 验证通过
		else {
			if (sessionMap == null) {
				System.out.println("sessionMap为null...");
			}
			sessionMap.put("user", u);
		}
	}

	//注入所有Session的Map集合
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
}
