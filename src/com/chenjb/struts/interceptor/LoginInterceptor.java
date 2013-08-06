package com.chenjb.struts.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.chenjb.domain.User;
import com.chenjb.struts.UserAware;
import com.chenjb.struts.action.BaseAction;
import com.chenjb.struts.action.LoginAction;
import com.chenjb.struts.action.RegAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings({ "serial", "rawtypes" })
public class LoginInterceptor implements Interceptor {

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation inv) throws Exception {
		BaseAction action = (BaseAction) inv.getAction();
		// 直接放行
		if (action instanceof LoginAction || action instanceof RegAction) {
			return inv.invoke();
		} else {
			HttpSession session = ServletActionContext.getRequest()
					.getSession();
			User user = (User) session.getAttribute("user");
			// 没登陆
			if (user == null) {
				return "login";
			} else {
				// 注入User对象
				if (action instanceof UserAware) {
					((UserAware) action).setUser(user);
				}
				return inv.invoke();
			}
		}
	}

}
