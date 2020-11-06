package com.asteroid.expression.dict.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.asteroid.expression.common.base.controller.GenericController;
import com.asteroid.expression.common.base.service.GenericService;

import com.asteroid.expression.dict.entity.Dict;
import com.asteroid.expression.dict.entity.QueryDict;
import com.asteroid.expression.dict.service.DictService;

/**
 *@author YuSai
 **/
@Controller
@RequestMapping("/dict")
public class DictController extends GenericController<Dict, QueryDict> {
	@Autowired
	private DictService dictService;
	@Override
	protected GenericService<Dict, QueryDict> getService() {
		return dictService;
	}
}