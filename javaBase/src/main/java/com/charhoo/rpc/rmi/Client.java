package com.charhoo.rpc.rmi;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * server 创建远程对象注册表，client获取远程对象注册表
 */
public class Client {

    public static void main(String[] args) {
        Registry registry = null;
        try {
//            Naming与LocateRegistry
            registry = LocateRegistry.getRegistry("127.0.0.1",7000);
            String[] list = registry.list();
            for (String str : list){
                System.out.println(str);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            IService iService = (IService)registry.lookup("iService");
            String result = iService.queryName("hello");
            System.out.println(result);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }


    }
}
