package org.mateusz.remote;

import java.rmi.Remote;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/3/13
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IRegistrationManager extends Remote {

    public String register(String nick,IClientObserver observer);

}
