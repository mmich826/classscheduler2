package report;

public class HourInfo {
	private int hour;
	private String time;
	
	public HourInfo(int hour, String time) {
		super();
		this.hour = hour;
		this.time = time;
	}
	
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "HourInfo [hour=" + hour + ", time=" + time + "]";
	}

}
