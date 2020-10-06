package gradle_spring_db_study.spring;

public class RegisterRequest {
	private String email;
	private String password;
	private String confrmPassword;
	private String name;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfrmPassword() {
		return confrmPassword;
	}
	public void setConfrmPassword(String confrmPassword) {
		this.confrmPassword = confrmPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isPasswordEqualToConfrmPassword() {
		return password.equals(confrmPassword);
	}
}
