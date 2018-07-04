package com.charhoo.os.controller;

import com.charhoo.os.service.MonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/monitor")
public class MonitorController {
	
	@Autowired
	private MonitorService monitorService;

	private static final Logger log = LoggerFactory.getLogger(MonitorController.class);
	
	/**
	 * redis集群节点状态
	 */
	@RequestMapping(value = "/clusterNodes", method = RequestMethod.GET)
	public Object clusterNodes(HttpServletRequest request) {
		log.info(" ");
		return monitorService.clusterNodes();
	}


}
