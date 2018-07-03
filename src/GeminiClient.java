/*
 * A very simple Gemini (gemini.com) bitcoin exchange market data client
 * This application shows the current best bid and ask prices of BTCUSD with order quantities.
 * Author: Erdi Gultekin
 * Used Libraries: Google Gson and Neovisionaries Websocket Client
 * https://github.com/TakahikoKawasaki/nv-websocket-client
 * https://github.com/google/gson
 */

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketExtension;
import com.neovisionaries.ws.client.WebSocketFactory;


public class GeminiClient
{
	private static final String SERVER = "wss://api.gemini.com/v1/marketdata/btcusd?bids=true&offers=true";
	private static final int TIMEOUT = 5000;
	static Map<BigDecimal, BigDecimal> bidBook;
	static Map<BigDecimal, BigDecimal> askBook;
	public static void main(String[] args) throws Exception
	{

		WebSocket ws = connect();
		bidBook = new HashMap <BigDecimal, BigDecimal>();
		askBook = new HashMap<BigDecimal, BigDecimal>();

		ws.addListener(new WebSocketAdapter() {
			@Override
			public void onTextMessage(WebSocket websocket, String message) throws Exception {

				Gson gson = new GsonBuilder().create();
				JsonParser parser = new JsonParser();
				JsonObject o = parser.parse(message).getAsJsonObject();
				GeminiMessage m = gson.fromJson(o, GeminiMessage.class);
				GeminiEvent[] events = m.getEvents();

				for(int i = 0; i< events.length; i++) {
					if(events[i].getSide().equals("bid")) {
						bidBook.put(events[i].getPrice(), events[i].getRemaining());
					}else {
						askBook.put(events[i].getPrice(), events[i].getRemaining());
					}
				}

				BigDecimal bestBid = Collections.max(bidBook.keySet());
				BigDecimal bestAsk = Collections.min(askBook.keySet());
				while(bidBook.get(bestBid).compareTo(new BigDecimal(0)) == 0) {
					bidBook.remove(bestBid);
					bestBid = Collections.max(bidBook.keySet());
				}
				while(askBook.get(bestAsk).compareTo(new BigDecimal(0)) == 0) {
					askBook.remove(bestAsk);
					bestAsk = Collections.min(askBook.keySet());
				}
				System.out.println("Best Bid: "+bestBid + " Quantity: " + bidBook.get(bestBid) + " - Best Ask: "+ bestAsk + " Quantity: " + askBook.get(bestAsk));
			}
		});
	}

	private static WebSocket connect() throws Exception
	{
		return new WebSocketFactory()
				.setConnectionTimeout(TIMEOUT)
				.createSocket(SERVER)
				.addExtension(WebSocketExtension.PERMESSAGE_DEFLATE)
				.connect();
	}
}