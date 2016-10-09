package au.edu.unimelb.tcp.client;

public class State {
	
	private String userId;
	private String password;
	private String identity;
	private String roomId;
	
	public State(String userId, String password, String identity, String roomId) {
		this.userId = userId;
		this.password = password;
		this.identity = identity;
		this.roomId = roomId;
		
	}
	
	public synchronized String getRoomId() {
		return roomId;
	}
	public synchronized void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getIdentity() {
		return identity;
	}
	
	
}
