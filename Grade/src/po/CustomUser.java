package po;

public class CustomUser extends UserInfo {

	private int grade;
	

	public UserGrade getUserGrade(){
		UserGrade userGrade = new UserGrade();
		userGrade.setId(this.getId());
		userGrade.setGrade(this.getGrade());
		return userGrade;
	}
	
	public User getUser(){
		User user = new User();
		user.setId(this.getId());
		return user;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "CustomUser [grade=" + grade + ", getUserGrade()="
				+ getUserGrade() + ", getUser()=" + getUser() + ", getGrade()="
				+ getGrade() + ", getId()=" + getId() + ", getUsername()="
				+ getUsername() + ", getAddress()=" + getAddress()
				+ ", getSex()=" + getSex() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}
