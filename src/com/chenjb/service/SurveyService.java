package com.chenjb.service;

import java.util.List;

import com.chenjb.domain.Page;
import com.chenjb.domain.Survey;
import com.chenjb.domain.User;

/**
 * 调查Service
 * 
 * @author ChenJianbin
 * @version 20130604
 */
public interface SurveyService {

	/**
	 * 新建调查
	 * 
	 * @param user
	 * @return
	 */
	public Survey newSurvey(User user);

	/**
	 * 查询我的全部调查
	 */
	public List<Survey> findMySurveys(Integer id);

	/**
	 * 查询调查对象,携带所有孩子
	 */
	public Survey getSurveyWithChildren(Integer sid);

	/**
	 * 查询调查对象，不携带子节点
	 */
	public Survey getSurvey(Integer sid);

	/**
	 * 更新调查
	 */
	public void updateSurvey(Survey s);

	/**
	 * 保存/更新page
	 */
	public void saveOrUpdatePage(Page model);

}
