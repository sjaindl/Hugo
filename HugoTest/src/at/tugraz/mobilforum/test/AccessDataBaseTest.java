package at.tugraz.mobilforum.test;

import at.tugraz.mobilforum.AccessDataBase;
import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;

public class AccessDataBaseTest extends AndroidTestCase {

	public void testInstance(){
		AccessDataBase instance = AccessDataBase.getInstance();
		assertNotNull(instance);
	}
	
	public void testConnection(){
		AccessDataBase instance = AccessDataBase.getInstance();
		instance.connect();
		assertTrue(instance.isConnected());
		instance.close();
		assertFalse(instance.isConnected());
	}

}
