package org.mateusz.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

    public static final String PROPERTIES = "src/org/mateusz/server/conf/conf.properties";
    public static final Logger logger = Logger.getLogger(Server.class.getSimpleName());

    private String hostname;
    private int port;

    public Server() {
        loadConfiguration();
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
        logger.info("Port: "+((Integer)port).toString());
    }

    public static void main(String[] args) {
        Server server = new Server();
    }

}
