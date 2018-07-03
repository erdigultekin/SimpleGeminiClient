
public class GeminiMessage {
	private String type;
	private int eventId;
	private int socket_sequence;
	private GeminiEvent[] events;
	private String timestamp;
	private String timestampms;
	
	public GeminiMessage(String type, int eventId, int socket_sequence, GeminiEvent[] events, String timestamp, String timestampms) {
		this.type = type;
		this.eventId = eventId;
		this.socket_sequence = socket_sequence;
		this.events = events;
		this.timestamp = timestamp;
		this.timestampms = timestampms;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getSocket_sequence() {
		return socket_sequence;
	}
	public void setSocket_sequence(int socket_sequence) {
		this.socket_sequence = socket_sequence;
	}
	public GeminiEvent[] getEvents() {
		return events;
	}
	public void setEvents(GeminiEvent[] events) {
		this.events = events;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getTimestampms() {
		return timestampms;
	}
	public void setTimestampms(String timestampms) {
		this.timestampms = timestampms;
	}

}
