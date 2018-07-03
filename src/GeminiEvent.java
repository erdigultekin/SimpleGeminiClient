import java.math.BigDecimal;

public class GeminiEvent {
	private String type;
	private String reason;
	private BigDecimal price;
	private BigDecimal delta;
	private BigDecimal remaining;
	private String side;
	
	public GeminiEvent(String type, String reason, BigDecimal price, BigDecimal delta, BigDecimal remaining, String side) {
		this.type = type;
		this.reason = reason;
		this.price = price;
		this.delta = delta;
		this.remaining = remaining;
		this.side = side;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getDelta() {
		return delta;
	}
	public void setDelta(BigDecimal delta) {
		this.delta = delta;
	}
	public BigDecimal getRemaining() {
		return remaining;
	}
	public void setRemaining(BigDecimal remaining) {
		this.remaining = remaining;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
}
