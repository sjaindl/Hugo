package at.tugraz.mobilforum;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		final EditText listener = ((EditText) findViewById(R.id.activity_register_username));
		listener.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				checkInput();
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

				findViewById(R.id.activity_register_button_next).setEnabled(
						false);

			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}
		});

		final EditText listener2 = ((EditText) findViewById(R.id.activity_register_edit_password));
		listener2.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				checkInput();
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

				findViewById(R.id.activity_register_button_next).setEnabled(
						false);

			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}
		});

		final EditText listener3 = ((EditText) findViewById(R.id.activity_register_edit_password_confirm));
		listener3.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				checkInput();
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

				findViewById(R.id.activity_register_button_next).setEnabled(
						false);

			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}
		});
	}

	void checkInput() {

		findViewById(R.id.activity_register_button_next).setEnabled(false);

		Editable username = ((EditText) findViewById(R.id.activity_register_username))
				.getText();
		Editable passwd = ((EditText) findViewById(R.id.activity_register_edit_password))
				.getText();
		Editable passwdconf = ((EditText) findViewById(R.id.activity_register_edit_password_confirm))
				.getText();

		if (username.toString().equals("")) {
			findViewById(R.id.activity_register_button_next).setEnabled(false);
		} else if (passwd.toString().equals(passwdconf.toString())) {

			// (AccessDataBase.getInstance().registerUser(username.toString(),passwd.toString(),
			// "") >= 0)
			// gehört in die if da unten, wenn die db geht
			if (username.toString().equals("Test")) // DB
			{
				findViewById(R.id.activity_register_button_next).setEnabled(
						true);
			} else {
				// Alert Start
				final Context context = this;
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);

				// set title
				alertDialogBuilder.setTitle(R.string.title_reg);

				alertDialogBuilder
						.setMessage(R.string.alert_reg)
						.setCancelable(false)
						.setPositiveButton(android.R.string.ok,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
									}
								});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
				// Alert Ende
				((EditText) findViewById(R.id.activity_register_username))
						.setText("");
				findViewById(R.id.activity_register_button_next).setEnabled(
						false);
			}
		} else {
			findViewById(R.id.activity_register_button_next).setEnabled(false);
		}
	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.main, menu); return true; }
	 */
}
