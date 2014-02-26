package br.com.soeirosantos.tradery;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.websocket.ClientEndpoint;

@ClientEndpoint
public class StockQuoteService {

	private static final String YAHOO_API_URL = "http://query.yahooapis.com/v1/public/yql?q=%s&env=%s&format=%s";
	private static final String QUERY = "select symbol,LastTradePriceOnly from yahoo.finance.quotes where symbol in ('YHOO','AAPL','GOOG','MSFT')";
	private static final String ENV = "http://datatables.org/alltables.env";
	private static final String FORMAT = "json";

	private static final String USER_AGENT_KEY = "User-Agent";
	private static final String CHARSET = "UTF-8";
	private static final String USER_AGENT_VALUE = "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/31.0.1650.63 Chrome/31.0.1650.63 Safari/537.36";

	public static String load() throws Exception {
		
		URL url = new URL(String.format(YAHOO_API_URL,
				URLEncoder.encode(QUERY, CHARSET),
				URLEncoder.encode(ENV, CHARSET),
				URLEncoder.encode(FORMAT, CHARSET)));

		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty(USER_AGENT_KEY, USER_AGENT_VALUE);

		return extractData(con);
	}

	private static String extractData(HttpURLConnection con) throws IOException {
		Scanner scanner = new Scanner(con.getInputStream());
		StringBuffer response = new StringBuffer();
		while (scanner.hasNext()) {
			response.append(scanner.next());
		}
		scanner.close();
		String res = response.toString();
		System.out.println(res);
		return res;
	}
	
}
