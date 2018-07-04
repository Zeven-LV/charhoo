package com.charhoo.os.controller;

import com.charhoo.os.service.ConnectionService;
import com.charhoo.os.session.ThisSession;
import com.charhoo.os.session.ThisSessionUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("connect")
public class ConnectionController {

    @Autowired
    private ConnectionService connectionService;

    /**
     * 添加redis集群
     * @param ipports
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("add")
    public Object add(@RequestParam(value = "ipports") String ipports, HttpServletRequest request){
        return connectionService.add(ipports);
    }

    /**
     * 已添加的redis列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("get")
    public Object add(HttpServletRequest request){
        return connectionService.get();
    }

    /**
     * 连接reids
     * @param connect
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("doConnect")
    public Object doConnect(@RequestParam(value="connect") String connect, HttpServletRequest request){
        ThisSession session = ThisSessionUtil.getSession();
        session.getConnect();
        ThisSessionUtil.applicationContext.getBean("");
        return connectionService.get();
    }
}
