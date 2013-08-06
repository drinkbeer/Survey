package com.chenjb.struts.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chenjb.domain.User;
import com.chenjb.service.UserService;
import com.chenjb.util.DataUtil;
import com.chenjb.util.ValidateUtil;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class RegAction extends BaseAction<User> {
	// 根据名称注入
	@Resource
	private UserService userService;

	// 确认密码
	private String confirmPassword;

	/**
	 * toRegPage：转到注册页面
	 * 
	 * @return "regView"
	 */
	public String toRegPage() {
		return "regView";
	}

	/**
	 * doReg:进行注册
	 * 
	 * @return success
	 */
	public String doReg() {
		model.setPassword(DataUtil.md5(model.getPassword()));
		userService.saveEntity(model);
		return "success";
	}

	@Override
	public void validate() {
		// 验证email非空
		if (!ValidateUtil.isValid(model.getEmail())) {
			this.addFieldError("email", "email是是必填项");
		}
		// 验证password非空
		if (!ValidateUtil.isValid(model.getPassword())) {
			this.addFieldError("password", "password是必填项");
		}
		// 验证nickname非空
		if (!ValidateUtil.isValid(model.getNickName())) {
			addFieldError("nickName", "nickName是必填项");
		}
		if (this.hasErrors()) {
			return;
		}

		// 验证密码一致性
		if (!model.getPassword().equals(confirmPassword)) {
			this.addFieldError("password", "密码不一致");
			return;
		}

		// 验证email有效性
		boolean b = userService.isRegisted(model.getEmail());
		if (b) {
			this.addFieldError("email", "邮箱已经占用");
		}
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
