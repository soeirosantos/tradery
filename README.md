tradery
=======

To run this example:
--------------------

__Server:__ Run the method `main` from `WebSocketServer`. (Alternatively, you can use a JEE7 container. Tested in WildFly Version 8.0.0.Final)

__Sender:__ Run the method `main` from `StockQuoteSender`.

__Client:__ use this code in any browser with websockets support. (Tested in Google Chrome Version 32.0.1700.107)

```javascript
	var ws = new WebSocket('ws://localhost:8080/websockets/stocks');
	
	ws.onopen = function(){
	   console.log('ws open!');
	}

	ws.onclose = function(){
	   console.log('ws closed');
	}

	ws.onerror = function(error){
	   console.log('Error detected: ' + error);
	}


	ws.onmessage = function(e){
	   var server_message = e.data;
	   console.log(server_message);
	}
```