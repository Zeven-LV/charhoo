package com.charhoo.os.controller;

import com.charhoo.os.model.ResponseModel;
import com.charhoo.os.service.BaseService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value = "string", description = "string类型")
@RestController
@RequestMapping("/base")
public class StringController {
	
	@Autowired
	private BaseService baseService;

	private static final Logger logger = LoggerFactory.getLogger(StringController.class);
	
	/**
	 * 增加
	 */
	@RequestMapping(value = "/string", method = RequestMethod.PUT)
	public ResponseModel stringPut(@RequestParam(value = "key") String key ,
									  @RequestParam(value = "value") String value , HttpServletRequest request) {
		String action = "set";
		logger.info(" action:{},key:{},value:{};",action, key, value);
		return baseService.stringAction(action, key, value);
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/string", method = RequestMethod.DELETE)
	public ResponseModel stringDelete(@RequestParam(value = "key") String key ,HttpServletRequest request) {
		String action = "del";
		logger.info(" action:{},key:{},value:{};",action, key, null);
		return baseService.stringAction(action, key, null);
	}

	/**
	 * 查找
	 */
	@RequestMapping(value = "/string", method = RequestMethod.GET)
	public ResponseModel stringGet(@RequestParam(value = "key") String key , HttpServletRequest request) {
		String action = "get";
		logger.info(" action:{},key:{},value:{};",action, key, null);
		return baseService.stringAction(action, key, null);
	}


}