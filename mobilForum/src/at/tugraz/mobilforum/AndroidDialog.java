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
import android.content.SharedPreferences;
 
public class AndroidDialog extends Activity {
 
 final private static int DIALOG_LOGIN = 1;
 
 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  showDialog(DIALOG_LOGIN);

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
   Button loginbutton = (Button) alertDialog
     .findViewById(R.id.btn_login);
   Button cancelbutton = (Button) alertDialog
     .findViewById(R.id.btn_cancel);
      
  
   if(!AccessDataBase.hasInstance()){
		AccessDataBase.setInstance(new AccessDataBase(this));
	}
	final AccessDataBase db = AccessDataBase.getInstance();
   
 
   
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
    		
  	    SharedPreferences sp=getSharedPreferences("Login", 0);
  	    SharedPreferences.Editor Ed=sp.edit();
  	    Ed.putString("userId", Integer.toString(userId));                 
  	    Ed.commit(); 
    	
    	if(userId != 0 )    		
    	{	   		
          alertDialog.dismiss();     
          Toast.makeText(     		 
          AndroidDialog.this,
          "Loggon Successful ",
          Toast.LENGTH_LONG).show();
    	}
    	else
    	{
    	   alertDialog.dismiss(); 
    	  Toast.makeText(
    	  AndroidDialog.this,
    	  "Wrong Login, please try again ",
    	  Toast.LENGTH_LONG).show();
    	}
    }
   });
 
   cancelbutton.setOnClickListener(new View.OnClickListener() {
 
    @Override
    public void onClick(View v) {
     alertDialog.dismiss();
     Toast.makeText(
     AndroidDialog.this,
     "Incorrect",
     Toast.LENGTH_LONG).show();
    }
   });
   break;
  }
 }
}