package com.chenjb.struts.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chenjb.domain.Survey;
import com.chenjb.domain.User;
import com.chenjb.service.SurveyService;
import com.chenjb.struts.UserAware;

/**
 * 调查Action
 * 
 * @author ChenJianbin
 * @version 20130604
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware {

	@Resource
	private SurveyService surveyService;

	// 接收sid参数
	private Integer sid;

	// 接受Session中的User对象
	private User user;

	// mySurveys
	private List<Survey> mySurveys;

	/**
	 * 新建调查
	 * 
	 * @return "designSurveyPage"
	 */
	public String newSurvey() {
		return "designSurveyPage";
	}

	/**
	 * 该方法在newSurvey()之前而且还在modelDriven的getModel之前先执行
	 */
	public void prepareNewSurvey() {
		this.model = surveyService.newSurvey(user);
	}

	/**
	 * 我的调查
	 * 
	 * @return "mySurveyListPage"
	 */
	public String mySurveys() {
		this.mySurveys = surveyService.findMySurveys(user.getId());
		return "mySurveyListPage";
	}

	/**
	 * 设计调查
	 * 
	 * @return "designSurveyPage"
	 */
	public String designSurvey() {
		return "designSurveyPage";
	}

	/**
	 * 此方法在designSurvey()之前执行，也在modelDriven的getModel之前先执行
	 */
	public void prepareDesignSurvey() {
		this.model = surveyService.getSurveyWithChildren(sid);
	}

	/**
	 * 编辑调查
	 * 
	 * @return "editSurveyPage"
	 */
	public String editSurvey() {
		return "editSurveyPage";
	}

	/**
	 * 此方法在editSurvey()之前执行
	 */
	public void prepareEditSurvey() {
		this.model = surveyService.getSurvey(sid);
	}

	/**
	 * 更新调查
	 * 
	 * @return "designSurveyAction"
	 */
	public String updateSurvey() {
		this.sid = model.getId();
		// System.out.println("sid：" + sid+"   modelID: "+model.getId());
		model.setUser(this.user);
		surveyService.updateSurvey(model);
		return "designSurveyAction";
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}

	// 接受sid参数
	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getSid() {
		return sid;
	}
}
