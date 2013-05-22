package at.tugraz.mobilforum;

import java.util.Date;

public class Entry {
	
	String username;
	String userpicture;
	String usersignature;
	String entrytext;
	java.util.Date date;
	int rating;
	
	public Entry(){
		
		this.username = "";
		this.userpicture = "";
		this.usersignature = ""; 
		this.entrytext = "";
		this.date = new Date();
		this.rating = 0;
	}

	public Entry(String username, String userpicture, String usersignature, 
			String entrytext, Date date, int rating){
		this.username = username;
		this.userpicture = userpicture;
		this.usersignature = usersignature;
		this.entrytext = entrytext;
		this.date = date;
		this.rating = rating;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpicture() {
		return userpicture;
	}
	public void setUserpicture(String userpicture) {
		this.userpicture = userpicture;
	}
	public String getUsersignature() {
		return usersignature;
	}
	public void setUsersignature(String usersignature) {
		this.usersignature = usersignature;
	}
	public String getEntrytext() {
		return entrytext;
	}
	public void setEntrytext(String entrytext) {
		this.entrytext = entrytext;
	}
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
}
