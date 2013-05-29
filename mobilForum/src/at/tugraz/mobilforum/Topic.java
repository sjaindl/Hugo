package at.tugraz.mobilforum;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Topic {

	private String title;
	private Date date;
	private String username;
	private int topicid;
	
	public Topic(int topicid, String title, long date, String username){
		this.topicid = topicid;
		this.date = new Date(date);
		this.username = username;
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime(){
		DateFormat df = new SimpleDateFormat("hh:mm",Locale.getDefault()); 
	    return df.format(this.date);
	}
	
	public String getDate(){
			DateFormat df = new SimpleDateFormat("dd.MM.",Locale.getDefault()); 
		    return df.format(this.date);
		}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTopicid() {
		return topicid;
	}

	public void setTopicid(int topicid) {
		this.topicid = topicid;
	}
	

	
	
	
}
