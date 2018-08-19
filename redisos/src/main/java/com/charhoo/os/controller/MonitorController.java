package com.charhoo.os.controller;

import com.charhoo.os.model.ResponseModel;
import com.charhoo.os.service.MonitorService;
import com.charhoo.os.utils.CurrentConnectUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value = "monitor", description = "监控")
@RestController
@RequestMapping("/monitor")
public class MonitorController {
	
	@Autowired
	private MonitorService monitorService;

	private static final Logger logger = LoggerFactory.getLogger(MonitorController.class);
	
	/**
	 * redis集群节点状态
	 */
	@RequestMapping(value = "/clusterNodes", method = RequestMethod.GET)
	public ResponseModel clusterNodes(HttpServletRequest request) {
		logger.info(" clusterNodes");
		if(CurrentConnectUtil.instants().getMap() == null){
			return ResponseModel.getInstance(ResponseModel.STATUS_FAIL,null,"当前没有连接redis服务！");
		}
		return monitorService.clusterNodes();
	}

	/**
	 * 各节点key的数量
	 */
	@RequestMapping(value = "/clusterDbSize", method = RequestMethod.GET)
	public ResponseModel clusterDbSize(HttpServletRequest request) {
		logger.info(" clusterDbSize");
		if(CurrentConnectUtil.instants().getMap() == null){
			return ResponseModel.getInstance(ResponseModel.STATUS_FAIL,null,"当前没有连接redis服务！");
		}
		return monitorService.clusterDbSize();
	}

	/**
	 * 集群状态
	 */
	@RequestMapping(value = "/clusterInfo", method = RequestMethod.GET)
	public ResponseModel clusterInfo(HttpServletRequest request) {
		logger.info(" clusterInfo");
		if(CurrentConnectUtil.instants().getMap() == null){
			return ResponseModel.getInstance(ResponseModel.STATUS_FAIL,null,"当前没有连接redis服务！");
		}
		return monitorService.clusterInfo();
	}

	/**
	 * 集群状态
	 */
	@RequestMapping(value = "/nodeInfo", method = RequestMethod.GET)
	public ResponseModel nodeInfo(HttpServletRequest request) {
		logger.info(" nodeInfo");
		if(CurrentConnectUtil.instants().getMap() == null){
			return ResponseModel.getInstance(ResponseModel.STATUS_FAIL,null,"当前没有连接redis服务！");
		}
		return monitorService.nodeInfo();
	}


}
