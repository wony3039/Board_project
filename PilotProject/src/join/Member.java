package join;

import java.util.Date;

public class Member {
	private String id;
	private String name;
	private String password;
	private String answer;
	private Date regDate;

	
	public Member(String id, String name, String password, String answer, Date regDate) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.answer = answer;
		this.regDate = regDate;
	}

	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getAnswer() {
		return answer;
	}

	public String getPassword() {
		return this.password;
	}

	public Date getRegDate() {
		return this.regDate;
	}

	public boolean matchPassword(String pwd) {
		return this.password.equals(pwd);
	}

	public void changePassword(String newPwd) {
		this.password = newPwd;
	}
}