package classscheduler;

public class Activity {
	private String activityCode;
	private String activityName;
	private int capacity;
	private String activityLeader;
	private String location;
	private String altLocation;
	private int enrollment;
	
	private String time;


	public boolean isFull() {
		return (capacity == enrollment);
	}
	
	public boolean enrollmentIncr() {
		if (enrollment < capacity) {
			enrollment++;
			return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "Activity [activityCode=" + activityCode + ", activityName="
				+ activityName + ", capacity=" + capacity + ", enrollment="
				+ enrollment + ", activityLeader=" + activityLeader
				+ ", location=" + location + ", altLocation=" + altLocation
				+ "]";
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getActivityLeader() {
		return activityLeader;
	}

	public void setActivityLeader(String activityLeader) {
		this.activityLeader = activityLeader;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAltLocation() {
		return altLocation;
	}

	public void setAltLocation(String altLocation) {
		this.altLocation = altLocation;
	}

	public int getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(int enrollment) {
		this.enrollment = enrollment;
	}


}