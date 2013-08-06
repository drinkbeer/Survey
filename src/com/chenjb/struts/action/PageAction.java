package com.chenjb.struts.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chenjb.domain.Page;
import com.chenjb.domain.Survey;
import com.chenjb.service.SurveyService;

/**
 * 页面Action
 * 
 * @author ChenJianbin
 */
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class PageAction extends BaseAction<Page> {
	private Integer sid;
	private Integer pid;

	@Resource
	private SurveyService surveyService;

	/**
	 * 转到增加/编辑page页面
	 * 
	 * @return "addPagePage"
	 */
	public String toAddPage() {
		return "addPagePage";
	}

	/**
	 * 保存/更新Page
	 */
	public String saveOrUpdatePage() {
		Survey s = new Survey();
		s.setId(sid);
		model.setSurvey(s);
		surveyService.saveOrUpdatePage(model);
		return "designSurveyAction";
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

}
