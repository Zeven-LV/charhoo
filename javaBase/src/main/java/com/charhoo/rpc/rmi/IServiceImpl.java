package com.charhoo.rpc.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 为什么继承UnicastRemoteObject？
 * Server端的main方法在创建一个远程对象来提供服务时，此远程对象必须被导出才能被远程调用者调用。
 * 静态方法UnicastRemoteObject.exportObject(）负责导出我们定义好的远程对象，
 * 并用任意一个tcp端口来接收远程方法调用，同时，它还会返回一个存根，这个存根将会发送给client端进行调用。
 */
public class IServiceImpl extends UnicastRemoteObject implements IService {


    public IServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String queryName(String no) throws RemoteException {
        System.out.println("hello" + no);
        return String.valueOf(System.currentTimeMillis());
    }
}
