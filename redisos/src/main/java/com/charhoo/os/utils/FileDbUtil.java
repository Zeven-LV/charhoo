package com.charhoo.os.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileDbUtil {

    private static Logger logger = LoggerFactory.getLogger(FileDbUtil.class);

    private final static String dbDir = FileDbUtil.class.getResource("/db").getPath();
    private final static String nextLine = "\r\n";

    public static List<String> getData(String fileName){
        List<String> resultList = new ArrayList<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try{
            fileReader = new FileReader(dbDir+"/"+fileName+".db");
            bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                resultList.add(line);
            }
            return resultList;
        }catch (Exception e){
            logger.error(" get fileDb error:{}",e);
            return null;
        }finally {
            if(fileReader != null){
                try {
                    fileReader.close();
                }catch (Exception e){
                    logger.error(" fileReader close error:{}",e);
                }
            }
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                }catch (Exception e){
                    logger.error(" bufferedReader close error:{}",e);
                }
            }
        }
    }

    public static int addData(String fileName, String value){
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(dbDir+"/"+fileName+".db", true);
            fileWriter.write(nextLine+value);
            return 1;
        }catch (Exception e){
            logger.error(" add data error:{}",e);
            return 0;
        }finally {
            if(fileWriter != null){
                try {
                    fileWriter.close();
                }catch (Exception e){
                    logger.error(" fileWriter close error:{}",e);
                }
            }

        }
    }

    /**
     * newValue替换OldValue
     * newValue为空则删除OldValue
     * @param fileName
     * @param oldValue
     * @param newValue
     * @return
     */
    public static int updateData(String fileName, String oldValue, String newValue){
        try{
            List<String> list = getData(fileName);
            String newData = "";
            for(String data : list){
                if (!data.equals(oldValue)){
                    newData = newData + nextLine + data;
                }else{
                    if(newValue != null){
                        newData = newData + nextLine + newValue;
                    }
                }
            }
            clearData(fileName);
            addData(fileName,newData);
            return 1;
        }catch (Exception e){
            logger.error("",e);
            return 0;
        }

    }

    public static int clearData(String fileName){
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(dbDir+"/"+fileName+".db");
            fileWriter.write("");
            return 1;
        }catch (Exception e){
            logger.error(" clear data error:{}",e);
            return 0;
        }finally {
            if(fileWriter != null){
                try {
                    fileWriter.close();
                }catch (Exception e){
                    logger.error(" fileWriter close error:{}",e);
                }
            }

        }
    }

}
