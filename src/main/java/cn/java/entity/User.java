package cn.java.entity;

public class User {

	private String username;
	
	private String pwd;
	
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", pwd=" + pwd + ", id=" + id + "]";
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
	
	
}
