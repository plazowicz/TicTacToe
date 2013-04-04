package org.mateusz.client;

import org.mateusz.remote.IClientObserver;
import org.mateusz.remote.IGameManager;
import org.mateusz.remote.IUserManager;
import org.mateusz.utils.PlayerSymbol;

import java.rmi.Naming;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/3/13
 * Time: 3:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Client {


    public static void main(String[] args) {
        if( args.length != 4 ) {
            System.out.println("Usage: java <IP> <PORT> <NICK> <CLIENT_IP>");
            System.exit(-1);
        }
        try {
            System.setProperty("java.rmi.server.hostname",args[3]);
            String url = "rmi://"+args[0]+":"+args[1];
            System.out.println(url);
            IUserManager manager = (IUserManager) Naming.lookup(url+"/UserManager");
            IClientObserver clientObserver = new ClientObserver();
            manager.register(args[2], clientObserver);
            IGameManager gameManager = (IGameManager) Naming.lookup(url+"/GameManager");
            gameManager.createGameWithHuman(PlayerSymbol.CIRCLE,args[2]);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
