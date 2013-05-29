package at.tugraz.mobilforum;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
			String entrytext, long date, int rating, String time){
		this.username = username;
		this.userpicture = userpicture;
		this.usersignature = usersignature;
		this.entrytext = entrytext;
		this.date = new Date(date);    
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
	public String getDate() {
		DateFormat df = new SimpleDateFormat("dd.MM.",Locale.getDefault()); 
	    return df.format(this.date);
	}
	
	public void setDate(long date){
		this.date = new Date(date);
	}
<<<<<<< HEAD
=======
	
	public String getTime() {
		DateFormat df = new SimpleDateFormat("hh:mm",Locale.getDefault()); 
	    return df.format(this.date);
	}
	
>>>>>>> master
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
<<<<<<< HEAD
=======
	
	public String toString(){
		return this.username + this.userpicture + this.getEntrytext() + this.getRating() + this.getUsersignature();
	}
	
	public String getShortEntryText(){
		String shorttext ="";
		return shorttext;
	}
>>>>>>> master
}
