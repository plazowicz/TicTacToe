package org.mateusz.client;

import org.mateusz.model.Map;
import org.mateusz.remote.IClientObserver;
import org.mateusz.remote.IGameListener;
import org.mateusz.remote.IGameManager;
import org.mateusz.remote.IUserManager;
import org.mateusz.utils.PlayerSymbol;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

/**
 * Created with IntelliJ IDEA.
 * User: mateusz
 * Date: 4/3/13
 * Time: 3:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Client {


    public static final Logger logger = Logger.getLogger(Client.class.getSimpleName());

    private static Client client;
    private static IGameManager gameManager;
    private static IUserManager userManager;
    private static PlayerSymbol symbol;
    private static PlayerSymbol winner;

    private static String rmiRegistry;
    private static boolean running = true;
    private static Map map;

    private Client() {

    }

    public static Client clientInstance() {
        if( client == null ) {
            client = new Client();
        }
        return client;
    }

    public static void createGame(String nick) {
        try {
            System.out.println("Pleasy choose symbol: x or o");
            switch( System.in.read() ) {
                case 'x':
                    symbol = PlayerSymbol.CROSS;
                    break;
                case 'o':
                    symbol = PlayerSymbol.CIRCLE;
                    break;
                default:
                    System.out.println("There is no such symbol");
                    System.exit(-1);
            }
            IGameListener gl = gameManager.createGameWithHuman(symbol,nick);
            while( !gl.playerDidJoin() )
                sleep(10);
            gameManager.startGame(nick);
            map = new Map();
            Scanner in = new Scanner(System.in);
            while( running ) {
                System.out.println("Please give coordinates of your move");
                int x = in.nextInt();
                int y = in.nextInt();
                logger.info("Move coordinates: x="+x+", y="+y);
                gl.makeMove(new int[]{ x, y});
                map.setFieldValue(x,y,symbol);
                map.print();
                if( (winner = gl.gameDidFinish()) != null ) {
                    System.out.println("The winner is "+winner.toString());
                    System.exit(-1);
                }
                while( !gl.isOpponentMoveReady() )
                    sleep(10);
                int[] opponentMove = gl.getOpponentMove();
                map.setFieldValue(opponentMove[0],opponentMove[1],PlayerSymbol.OPPOSITE_SYMBOLS.get(symbol));
                map.print();
                if( (winner = gl.gameDidFinish()) != null ) {
                    System.out.println("The winner is "+winner.toString());
                    System.exit(-1);
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void listGames() throws RemoteException {
        for(String owner : gameManager.listGames())
            System.out.println(owner);
    }

    public static void joinGame(String nick) {
        System.out.println("These are possible games:");
        try {
            listGames();
            System.out.println("Please choose game");
            Scanner in = new Scanner(System.in);
            String owner = in.nextLine();
            IGameListener gl = gameManager.joinGame(owner, nick);
            map = new Map();
            while( running ) {
                while( !gl.isOpponentMoveReady() )
                    sleep(10);
                int[] opponentMove = gl.getOpponentMove();
                map.setFieldValue(opponentMove[0],opponentMove[1],PlayerSymbol.OPPOSITE_SYMBOLS.get(symbol));
                map.print();
                if( (winner = gl.gameDidFinish()) != null ) {
                    System.out.println("The winner is "+winner.toString());
                    System.exit(-1);
                }
                System.out.println("Please give coordinates of your move");
                int x = in.nextInt();
                int y = in.nextInt();
                logger.info("Move coordinates: x="+x+", y="+y);
                gl.makeMove(new int[]{ x, y});
                map.setFieldValue(x,y,symbol);
                map.print();
                if( (winner = gl.gameDidFinish()) != null ) {
                    System.out.println("The winner is "+winner.toString());
                    System.exit(-1);
                }

            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if( args.length != 4 ) {
            System.out.println("Usage: java <IP> <PORT> <NICK> <CLIENT_IP>");
            System.exit(-1);
        }
        try {
            System.setProperty("java.rmi.server.hostname",args[3]);
            rmiRegistry = "rmi://"+args[0]+":"+args[1];
            System.out.println(rmiRegistry);
            userManager = (IUserManager) Naming.lookup(rmiRegistry+"/UserManager");
            IClientObserver clientObserver = new ClientObserver();
            userManager.register(args[2], clientObserver);
            gameManager = (IGameManager) Naming.lookup(rmiRegistry+"/GameManager");
            System.out.println("Please choose if you'd like to (c)reate game or (j)oin game");
            switch( System.in.read() ) {
                case 'c':
                    System.in.read();
                    createGame(args[2]);
                    break;
                case 'j':
                    System.in.read();
                    joinGame(args[2]);
                    break;
                default:
                    System.out.println("wrong option");
                    System.exit(-1);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
