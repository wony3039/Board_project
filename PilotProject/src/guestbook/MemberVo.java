package guestbook;

import java.util.Map;

public class MemberVo {
	private String guest_name;
	private String password;
	private String message;

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

	public void validate(Map<String, Boolean> errors) {
		this.checkEmpty(errors, this.guest_name, "guest_name");
		this.checkEmpty(errors, this.password, "password");
		this.checkEmpty(errors, this.message, "message");
	}

	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if (value == null || value.isEmpty()) {
			errors.put(fieldName, Boolean.TRUE);
		}

	}
}