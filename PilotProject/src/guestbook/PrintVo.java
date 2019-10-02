package guestbook;

public class PrintVo {
	private String message_id;
	private String guest_name;
	private String password;
	private String message;
	
	public PrintVo() {
	}

	public PrintVo(String message_id, String guest_name, String password, String message) {
		this.message_id = message_id;
		this.guest_name = guest_name;
		this.password = password;
		this.message = message;
	}

	
	public String getMessage_id() {
		return this.message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public String getGuest_name() {
		return this.guest_name;
	}

	public void setGuest_name(String guest_name) {
		this.guest_name = guest_name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}