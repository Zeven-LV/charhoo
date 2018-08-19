package com.charhoo.os.controller;

import com.charhoo.os.service.ConnectionService;
import com.charhoo.os.session.ThisSession;
import com.charhoo.os.session.ThisSessionUtil;
import com.charhoo.os.utils.CurrentConnectUtil;
import io.swagger.annotations.Api;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;

@Api(value = "connect", description = "连接")
@RestController
@RequestMapping("/connect")
public class ConnectionController {

    private Logger logger = LoggerFactory.getLogger(ConnectionController.class);

    @Autowired
    private ConnectionService connectionService;

    /**
     * 添加redis集群
     * @param ipports
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(@RequestParam(value = "ipports") String ipports, HttpServletRequest request){
        return connectionService.add(ipports);
    }

    /**
     * 已添加的redis列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getHistory", method = RequestMethod.GET)
    public Object getHistory(HttpServletRequest request){
        return connectionService.get();
    }

    /**
     * 获取当前连接
     * @param request
     * @return
     */
    @RequestMapping(value = "/getCurrentConnection", method = RequestMethod.GET)
    public Object getCurrentConnection(HttpServletRequest request){
        return CurrentConnectUtil.instants().getMap();
    }

    /**
     * 连接reids
     * @param connect
     * @param request
     * @return
     */
    @RequestMapping(value = "/doConnect", method = RequestMethod.POST)
    public Object doConnect(@RequestParam(value="connect") String connect, HttpServletRequest request){
        //当前是否有连接
        if(CurrentConnectUtil.instants().hasConnect()){
            CurrentConnectUtil.instants().set(null,0);
        }
        logger.info("new connection addr :{}" ,connect);

//        ServletContext.

//        ThisSession session = ThisSessionUtil.getSession();
//        session.getConnect();
//        ThisSessionUtil.applicationContext.getBean("");
        CurrentConnectUtil.instants().set(connect,System.currentTimeMillis());
        return connectionService.get();
    }

    /**
     * 断开连接reids
     * @param request
     * @return
     */
    @RequestMapping(value = "/unConnect", method = RequestMethod.GET)
    public Object unConnect(HttpServletRequest request){
        CurrentConnectUtil.instants().set(null,0);
        return "已断开redis服务连接！";
    }

}
