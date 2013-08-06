package com.chenjb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chenjb.dao.BaseDao;
import com.chenjb.domain.Page;
import com.chenjb.domain.Survey;
import com.chenjb.domain.User;
import com.chenjb.service.SurveyService;

@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {
	@Resource(name = "pageDao")
	private BaseDao<Page> pageDao;

	@Resource(name = "surveyDao")
	private BaseDao<Survey> surveyDao;

	/**
	 * 新建调查
	 */
	@Override
	public Survey newSurvey(User user) {
		Survey s = new Survey();
		// 设置Survey与User之间的关联
		s.setUser(user);
		Page p = new Page();
		// 设置Survey与Page之间的关联
		p.setSurvey(s);
		s.getPages().add(p);
		surveyDao.saveEntity(s);
		pageDao.saveEntity(p);
		return s;
	}

	/**
	 * 查询我的全部调查
	 */
	@Override
	public List<Survey> findMySurveys(Integer id) {
		String hql = "from Survey s where s.user.id=?";
		return surveyDao.findEntityByHQL(hql, id);
	}

	/**
	 * 查询调查对象,携带所有孩子
	 */
	@Override
	public Survey getSurveyWithChildren(Integer sid) {
		Survey s = surveyDao.getEntity(sid);
		for (Page p : s.getPages()) {
			p.getQuestions().size();
		}
		return s;
	}

	/**
	 * 查询调查对象，不携带子节点
	 */
	@Override
	public Survey getSurvey(Integer sid) {
		return surveyDao.getEntity(sid);
	}

	/**
	 * 更新调查
	 */
	@Override
	public void updateSurvey(Survey s) {
		surveyDao.updateEntity(s);
	}

	/**
	 * 保存/更新page
	 */
	@Override
	public void saveOrUpdatePage(Page p) {
		pageDao.saveOrUpdateEntity(p);
	}

}
