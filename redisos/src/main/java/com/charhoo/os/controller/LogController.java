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
@RequestMapping("/log")
public class LogController {
	
	@Autowired
	private BaseService baseService;

	private static final Logger log = LoggerFactory.getLogger(LogController.class);
	
	/**
	 * redis基本操作日志
	 */
	@RequestMapping(value = "/actionLog", method = RequestMethod.GET)
	public Object actionLog(@RequestParam(value = "startTime") String startTime ,HttpServletRequest request) {
		log.info(" startTime:{};",startTime);
		return null;//baseService.stringAction(action, key, value);

	}


}
