package br.com.soeirosantos.tradery;

import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class StockQuoteSender {

	
	//TODO: it would be too much better to use 
	//      a persistent  connection instead to
	//      open one each time when is needed  
	//      send a message
	@OnOpen
	public void onOpen(Session session) throws Exception {
		session.getBasicRemote().sendText(StockQuoteService.load());
	}
	
	public static void main(String[] args) throws Exception {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		
		while(true){
			Thread.sleep(5000);
			container.connectToServer(StockQuoteSender.class, new URI("ws://localhost:8000/websockets/stocks"));
		}
	}

}
