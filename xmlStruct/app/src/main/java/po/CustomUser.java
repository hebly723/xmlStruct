package po;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

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

	public JSONObject toJson(){
		JSONObject json = new JSONObject();
		try {
			Log.v("Tag", "9");
			json.put("id", this.getId());
			Log.v("Tag", "10");
			json.put("grade", this.getGrade());
			Log.v("Tag", "11");
			json.put("username", this.getUsername());
			Log.v("Tag", "12");
			json.put("address", this.getAddress());
			Log.v("Tag", "13");
			json.put("sex", this.getSex());

//			JSONObject userJson = new JSONObject();
//			userJson.put("id", this.getUser().getId());
//			json.put("user", userJson);

//			JSONObject gradeJson = new JSONObject();
//			gradeJson.put("grade", this.getUserGrade().getGrade());
//			gradeJson.put("id", this.getUserGrade().getId());

//			json.put("userGrade", gradeJson);
//			json.put("user", this.getUser());
//			json.put()
			Log.v("Tag", "14");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
	
}
