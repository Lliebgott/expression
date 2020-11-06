package com.asteroid.expression.dict.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.asteroid.expression.common.base.service.GenericService;
import com.asteroid.expression.common.base.dao.GenericDao;

import com.asteroid.expression.dict.entity.Dict;
import com.asteroid.expression.dict.entity.QueryDict;
import com.asteroid.expression.dict.dao.DictDao;

/**
 *@author YuSai
 **/
@Service("dictService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class DictService extends GenericService<Dict, QueryDict> {
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private DictDao dictDao;
	@Override
	protected GenericDao<Dict, QueryDict> getDao() {
		return dictDao;
	}
}