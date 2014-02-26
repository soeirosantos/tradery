package br.com.soeirosantos.tradery;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.glassfish.tyrus.server.Server;

public class WebSocketServer {
	 
    public static void main(String[] args) {
        runServer();
    }
 
    public static void runServer() {
        Server server = new Server("localhost", 8000, "/websockets", StockQuoteEndpoint.class);
 
        try {
            server.start();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Please press a key to stop the server.");
            reader.readLine();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }
}