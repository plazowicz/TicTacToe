package org.mateusz.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/3/13
 * Time: 3:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Server {

    public static final String PROPERTIES = "conf/conf.properties";
    public static final Logger logger = Logger.getLogger(Server.class.getSimpleName());

    private static Server server;

    private String hostname;
    private Integer port;
    private UserManager userManager;
    private GameManager gameManager;
    private String registryUrl;

    private Server() {
        loadConfiguration();
    }

    public static Server serverInstance() {
        if( server == null ) {
            server = new Server();
        }
        return server;
    }

    public String getRegistryUrl() {
        return registryUrl;
    }

    public void start() throws RemoteException, MalformedURLException {
        System.setProperty("java.rmi.server.hostname",hostname);
        LocateRegistry.createRegistry(port);
        registryUrl = "rmi://127.0.0.1:"+port.toString();
        userManager = new UserManager();
        Naming.rebind(registryUrl+"/UserManager", userManager);
        gameManager = new GameManager(userManager);
        Naming.rebind(registryUrl+"/GameManager", gameManager);
    }

    private void loadConfiguration() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PROPERTIES));
        } catch (IOException e) {
            logger.severe("Loading configuration failed: "+e.getLocalizedMessage());
        }
        hostname = properties.getProperty("hostname");
        logger.info("Hostname: "+hostname);
        port = Integer.parseInt(properties.getProperty("port"));
        logger.info("Port: "+(port).toString());
    }



    public static void main(String[] args) {
        Server server = Server.serverInstance();
        try {
            server.start();
        } catch (RemoteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
