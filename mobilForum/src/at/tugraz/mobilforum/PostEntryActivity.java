package at.tugraz.mobilforum;

import java.io.File;
import java.net.URISyntaxException;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Camera;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class PostEntryActivity extends Activity {
	public Toast toast;

	private static final String imagefilepath = Environment.getExternalStorageDirectory().getAbsolutePath();
	private static final int REQUEST_CAMERA = 99;
	private static final int REQUEST_IMAGE_FILE = 1;
	private Uri uri = null;
	private int topicid = 1;
	private int userid = 1;
	private int categoryid = 1;

	private Entry entry = null;

	@Override
	@SuppressLint("ShowToast")
	protected void onCreate(Bundle savedInstanceState) {
		  this.topicid = getIntent().getIntExtra("topicid", 1);
		  this.categoryid = getIntent().getIntExtra("categoryid", 1);

		AccessDataBase.setInstance(new AccessDataBase(this.getApplicationContext()));
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post);
		toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		this.entry = new Entry();

		Button uploadButton = (Button) this.findViewById(R.id.imageUploadButton);

		final PostEntryActivity pa = this;
		uploadButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder builder = new AlertDialog.Builder(pa);
				builder.setTitle(R.string.dialog_imageupload);
				builder.setItems(R.array.dialog_imageupload_items, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch(which){

						case 0:
						imageFromCamera();	
						break;

						case 1:
						imageFromFileSystem();	
						break;

						default:
						break;
						}
					}

					private void imageFromFileSystem() {
						Intent intent = new Intent(Intent.ACTION_GET_CONTENT); 
				        intent.setType("*/*"); 
				        intent.addCategory(Intent.CATEGORY_OPENABLE);

				        try {
				            startActivityForResult(
				                    Intent.createChooser(intent, "Select a File to Upload"),
				                    REQUEST_IMAGE_FILE);
				        } catch (android.content.ActivityNotFoundException ex) {
				            // Potentially direct the user to the Market with a Dialog
				        }

					}

					private void imageFromCamera() {
						File f = new File(imagefilepath + "/tmp");
						if(!f.exists()){
							boolean b = f.mkdir();
						}
						uri = Uri.fromFile(new File(imagefilepath + "/tmp","uploadedImage.jpg")); 	
						Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
						Intent chooser = Intent.createChooser(intent, "Select camera");		
						startActivityForResult(chooser, REQUEST_CAMERA);
					}
				});

				builder.create();
				builder.show();
			}
		}
		);
	}

	void autoUpdateMenuItemState(Menu menu)
	{
		final EditText inputText = (EditText)findViewById(R.id.newPostText);
		final MenuItem postButton = menu.findItem(R.id.btn_new_post);
		toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, inputText.getTop() + 50);

		final PostEntryActivity ps = this;

		postButton.setOnMenuItemClickListener(new OnMenuItemClickListener(){

			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				if(inputText.getText().length()>=1){
					entry.setEntrytext(inputText.getText().toString());
					AccessDataBase.getInstance().postEntry(topicid, userid, entry);
					Toast.makeText(ps, "Entry posted", Toast.LENGTH_SHORT).show();
					Intent i = new Intent();
					i.putExtra("topicid",ps.topicid);
					i.putExtra("categorycid",ps.categoryid);
					
					i.setClass(getApplicationContext(), ReadEntriesActivity.class);
					startActivity(i);
					return true;
				}
				return false;
			}



		});

		inputText.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	        	int length = inputText.getText().length();

	        	if(length > 0 && length <= 1024)
	        		postButton.setEnabled(true);
	        	else
	        		postButton.setEnabled(false);

	        	if(length > 1014)
	        	{
	        		StringBuilder sb = new StringBuilder();
	        		sb.append(length);
	        		sb.append("/1024");
	        		toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, inputText.getTop() + 50);

	        		if(length > 1024)
	        			sb.append(" (TOO LONG!)");

	        		toast.setText(sb.toString());
	        		toast.show();
	        	}
	        }

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {}
	        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post, menu);
		autoUpdateMenuItemState(menu);
		return true;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		  super.onActivityResult(requestCode, resultCode, data);

		  if (resultCode == Activity.RESULT_OK) {


			  String imageurl = null;
			  switch (requestCode) {


			  case REQUEST_IMAGE_FILE:
		    	if(data != null){
		    	Uri uri = data.getData();
                Log.d("TAG", "File Uri: " + uri.toString());
                try {
					imageurl = getPath(this, uri);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                }
		    	break;

		    case REQUEST_CAMERA:
		    	Log.d("IMG","REQUEST_CAMERA in onActivityResult");

		   	 imageurl = imagefilepath + "/tmp" + "/uploadedImage.jpg";
		     break;
		   }
			  Options opt = new Options();
		    	opt.inScaled = true;
		    	opt.inSampleSize = 10;
		    	Bitmap mImageBitmap = BitmapFactory.decodeFile(imageurl, opt);
		    	ImageView iv = (ImageView) findViewById(R.id.imageUploadView);
		    	iv.setImageBitmap(mImageBitmap);
		    	iv.bringToFront();
		    	iv.invalidate();
		    	iv.setMaxHeight(500);
		    	iv.setMaxWidth(500);
		    	iv.setPadding(5, 5, 5, 5);

		    	String localfilepath = "";

		    	/**
		    	 * TODO: copy file to local app storage
		    	 */
		    	localfilepath = imageurl; // FIXXXME

		    	this.entry.setUploadedImageURI(localfilepath);


		  }
		 }

	private void copyFile(String path) {
		File oldfile = new File(path);

		// TODO Auto-generated method stub

	}

	public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor
                .getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        }

        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

}