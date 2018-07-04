package com.charhoo.os.service;

import com.charhoo.os.utils.FileDbUtil;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConnectionService {

    private final String fileName = "connect";

    public Object add(String value){
        return FileDbUtil.addData(fileName,value);
    }

    public Object get(){
        List<String> connectList = FileDbUtil.getData(fileName);
        List<Map<String, Object>> result = new ArrayList<>();
        for(String connecct : connectList){
            Map<String, Object> connectMap = new HashMap<>();
            if(!"".equals(connecct)){
                connectMap.put("connect",connecct);
                result.add(connectMap);
            }
        }
        return result;
    }

    public Object update(String oldValue, String newValue){
        return FileDbUtil.updateData(fileName, oldValue, newValue);

    }

}
