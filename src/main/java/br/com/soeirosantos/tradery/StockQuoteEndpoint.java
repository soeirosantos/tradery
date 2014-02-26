package br.com.soeirosantos.tradery;

import java.util.Set;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/stocks")
public class StockQuoteEndpoint {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	 
    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected ... " + session.getId());
    }
 
    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
    	Set<Session> openSessions = session.getOpenSessions();
        for (Session s : openSessions) {
          s.getBasicRemote().sendText(message);
        }
    	
    	System.out.println(message);
    }
 
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }
	
}
