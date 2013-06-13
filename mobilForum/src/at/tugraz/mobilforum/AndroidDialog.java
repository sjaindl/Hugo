package at.tugraz.mobilforum;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
 
public class AndroidDialog extends Activity {
 
 final private static int DIALOG_LOGIN = 1;
 private int categoryid = 0;
 private int topicid = 0;
 
 
 
 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  showDialog(DIALOG_LOGIN);
  this.topicid = getIntent().getIntExtra("topicid", 1);
  this.categoryid = getIntent().getIntExtra("categoryid", 1);
  

  
 }
 
 @Override
 protected Dialog onCreateDialog(int id) {
 
  AlertDialog dialogDetails = null;
 
  switch (id) {
  case DIALOG_LOGIN:
   LayoutInflater inflater = LayoutInflater.from(this);
   View dialogview = inflater.inflate(R.layout.dialog_layout, null);
 
   AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
   dialogbuilder.setTitle("Login");
   dialogbuilder.setView(dialogview);
   dialogDetails = dialogbuilder.create();
 
   break;
  }
 
  return dialogDetails;
 }
 
 @Override
 protected void onPrepareDialog(int id, Dialog dialog) {
 
  switch (id) {
  case DIALOG_LOGIN:
   final AlertDialog alertDialog = (AlertDialog) dialog;
   alertDialog.setCanceledOnTouchOutside(true);
   Button loginbutton = (Button) alertDialog
     .findViewById(R.id.btn_login);
   Button cancelbutton = (Button) alertDialog
     .findViewById(R.id.btn_cancel);
      
  
   if(!AccessDataBase.hasInstance()){
		AccessDataBase.setInstance(new AccessDataBase(this));
	}
	final AccessDataBase db = AccessDataBase.getInstance();
   
  	final AndroidDialog ad = this;
    
   
   loginbutton.setOnClickListener(new View.OnClickListener() {
 
    @Override
    public void onClick(View v) {
    	
    	 	
    	int userId;
    		     		
    	final Editable userName = ((EditText) alertDialog.findViewById(R.id.txt_name)).getText();
    	
    	final Editable passwdconf = ((EditText) alertDialog.findViewById(R.id.password)).getText();
    	//Log.d("user name", userName.toString());
    	//Log.d("user password", passwdconf.toString());
    	
    	/** TODO: fix database
    	 * 
    	 * 
    	 */
    	userId = db.approveUser(userName.toString(), passwdconf.toString());
    	Log.d("user id", Integer.toString(userId));
    	 
    	
    	if(userId != 0 )    		
    	{	   		
          alertDialog.dismiss();     
          Toast.makeText(     		 
          AndroidDialog.this,
          "Erfolgreich eingeloggt",
          Toast.LENGTH_LONG).show();
          SharedPreferences sp=getSharedPreferences("Login", 0);
    	  SharedPreferences.Editor Ed=sp.edit();
    	  Ed.putString("userId", Integer.toString(userId));                 
    	  Ed.commit(); 
    	  Intent i = new Intent();
    	  i.putExtra("topicid",ad.topicid);
    	  i.setClass(getApplicationContext(), ReadEntriesActivity.class);
    	  startActivity(i);
		}
    	else
    	{
    	   alertDialog.dismiss(); 
    	  Toast.makeText(
    	  AndroidDialog.this,
    	  "Benutzerdaten falsch, bitte versuche es nocheinmal",
    	  Toast.LENGTH_LONG).show();
    	  Intent i = new Intent();
			i.setClass(getApplicationContext(), AndroidDialog.class);
			startActivity(i);
    	}
    }
   });
    
   cancelbutton.setOnClickListener(new View.OnClickListener() {
 
    @Override
    public void onClick(View v) {
     alertDialog.dismiss();
     ad.finish();
    }
   });
   break;
  }
 }
}