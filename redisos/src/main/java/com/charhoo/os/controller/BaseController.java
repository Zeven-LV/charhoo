package com.charhoo.os.controller;

import com.charhoo.os.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/base")
public class BaseController {
	
	@Autowired
	private BaseService baseService;

	private static final Logger log = LoggerFactory.getLogger(BaseController.class);
	
	/**
	 * redis基本操作
	 */
	@RequestMapping(value = "/string", method = RequestMethod.GET)
	public Object stringAction(@RequestParam(value = "action") String action,
							 @RequestParam(value = "key") String key ,
							 @RequestParam(value = "value") String value ,HttpServletRequest request) {
		log.info(" action:{},key:{},value:{};",action, key, value);
		return baseService.stringAction(action, key, value);

	}

	@RequestMapping(value = "/hash", method = RequestMethod.GET)
	public Object hashAction(@RequestParam(value = "action") String action,
							 @RequestParam(value = "key") String key ,
							 @RequestParam(value = "value") String value ,HttpServletRequest request) {
		log.info(" action:{},key:{},value:{};",action, key, value);
		return baseService.hashAction(action, key, value);

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object listAction(@RequestParam(value = "action") String action,
							 @RequestParam(value = "key") String key ,
							 @RequestParam(value = "value") String value ,HttpServletRequest request) {
		log.info(" action:{},key:{},value:{};",action, key, value);
		return baseService.listAction(action, key, value);

	}


}
