package com.charhoo.rpc.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {
        Registry registry = null;
        IService iService = null;
        try {
            registry = LocateRegistry.createRegistry(7000);
            iService = new IServiceImpl();
            registry.bind("iService",iService);
            System.out.println("---------server-----------");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }


    }
}
