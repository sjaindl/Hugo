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
   
   Log.d("password","hugo");
   
 // blabla
   AccessDataBase inst = null;
   AccessDataBase.setInstance( inst);
   
 
   
   loginbutton.setOnClickListener(new View.OnClickListener() {
 
    @Override
    public void onClick(View v) {
    	
    	boolean test = true;
    	
    	int user_id = 0;
    		
    	
       		
    		final Editable userName = ((EditText) alertDialog.findViewById(R.id.txt_name)).getText();
    		Log.d("password",userName.toString());
    		final Editable passwdconf = ((EditText) alertDialog.findViewById(R.id.password)).getText();
    		Log.d("password", passwdconf.toString());
    		
    	//	user_id =	AccessDataBase.getInstance().approveUser(userName.toString(), passwdconf.toString());
    		
    	if( test)    		
    	{	
    		
    	  Bundle bundle = new Bundle();
          bundle.putString("USERID", Integer.toString(user_id));
    		
         alertDialog.dismiss();     
         Toast.makeText(
        		 
         AndroidDialog.this,
         "Thank you , you are in",
         Toast.LENGTH_LONG).show();
    	}
    	else
    	{
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
     "Wrong Login, please try again ",
     Toast.LENGTH_LONG).show();
    }
   });
   break;
  }
 }
}