package login;

import java.util.Map;

public class LoginVo {
	private String member_id;
	private String name;
	private String password;
	private String answer;
	private String regdate;

	public LoginVo(String member_id, String name, String password, String answer, String regdate) {
	
		this.member_id = member_id;
		this.name = name;
		this.password = password;
		this.answer = answer;
		this.regdate = regdate;
	}

	public LoginVo() {
		
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void validate(Map<String, Boolean> errors,String value) {
		checkEmpty(errors, value, "password");
		if(!password.equals(value)) {
			errors.put("noMatch", Boolean.TRUE);
		}
	}
	
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if (value == null || value.isEmpty()) {
			errors.put(fieldName, Boolean.TRUE);
		}
	}
	
}
