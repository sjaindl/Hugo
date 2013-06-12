/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package at.tugraz.mobilforum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.display.DisplayManager.DisplayListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SwipeActivity extends FragmentActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * three primary sections of the app. We use a {@link android.support.v4.app.FragmentPagerAdapter}
     * derivative, which will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will display the three primary sections of the app, one at a
     * time.
     */
    ViewPager mViewPager;

    @SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        
        
  	    SharedPreferences sp=getSharedPreferences("Login", 0);
  	    if(!sp.getBoolean("Login", true)){
  	        SharedPreferences.Editor Ed=sp.edit();
  	  	    Ed.clear();
  	  	    Ed.putString("userId", "0");                 
  	  	    Ed.commit(); 
  	    }


        if(!AccessDataBase.hasInstance()){
     		AccessDataBase.setInstance(new AccessDataBase(this));
     	}
        
        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());


        
        // Set up the action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        actionBar.setHomeButtonEnabled(false);

        // Specify that we will be displaying tabs in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set up the ViewPager, attaching the adapter and setting up a listener for when the
        // user swipes between sections.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mAppSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
        
    }
    
    @Override
    public void onBackPressed() {
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
       
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        Map<Integer,String> categories;
        
        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);

         	final AccessDataBase db = AccessDataBase.getInstance();
         	categories = db.getCategoryList();
        }

        @Override
        public Fragment getItem(int i) {
	
        	int id = getCatIDforPosition(i);
        	return new ListViewFragment().setData(id, categories.get(id));
        	
        }
//Jonny: getCount - soll den Wert aus der DB bekommen
        @Override
        public int getCount() {
            return categories.size();
        }
//Jonny: get PageTitle: soll den Namen der Category aus der DB bekommen
        @Override
        public CharSequence getPageTitle(int position) {
            return categories.get(getCatIDforPosition(position));
        }
        
        private int getCatIDforPosition(int p){
        	return (Integer)categories.keySet().toArray()[p];
        }
    }

    
    
    public static class ListViewFragment extends Fragment {
   	 
    	String category;
    	int catid;

    	public ListViewFragment setData(int catid, String cat){
    		this.category = cat;
    		this.catid = catid;
    		return this;
    	}
    	
       @Override
       public View onCreateView(LayoutInflater inflater, ViewGroup container,
               Bundle savedInstanceState) {
           View rootView = inflater.inflate(R.layout.activity_read_topics, container, false);
           Bundle args = getArguments();
           //DO STUFF HERE
//          ListView listview = (ListView) rootView.findViewById(R.id.listblabla);
//
//           ArrayList<String> list = new ArrayList<String>();
//           ArrayAdapter<String> adapter;
//           adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, list);
//          
//           for(int i = 0; i < 4; i++){
//          
//        	   list.add(i, getDatafromDB(i));
//        	   
//           }
//          listview.setAdapter(adapter);

       	 ListView lv;
       	 List<Topic> topics;
           lv = (ListView) rootView.findViewById(R.id.topicListView);
           AccessDataBase db = AccessDataBase.getInstance();
           topics = db.getTopicList(catid);
           
           
           
           final Context context = inflater.getContext();
           final ReadTopicsBaseAdapter readTopicsBaseAdapter;
           readTopicsBaseAdapter = new ReadTopicsBaseAdapter(context,topics);
           
           lv.setAdapter(readTopicsBaseAdapter);
           lv.setOnItemClickListener(new OnItemClickListener() {

   			@Override
   			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
   					long arg3) {
   				Intent i = new Intent();

   				i.putExtra("topicid", readTopicsBaseAdapter.getItem(arg2).getTopicid());
   				i.setClass(context, ReadEntriesActivity.class);
   				startActivity(i);
   			}
   			});

          return rootView;
       }
       
       
       public  String getDatafromDB(int CategoryNumber){
    	  	  //databasedummy
    	  	   //i ... kategorien
    	  	   ArrayList<String> list = new ArrayList<String>();
    	  	   ArrayList<String> fromDB = new ArrayList<String>();
    	  	   list.add(0, "David ist der Beste!");
    	  	   list.add(1, "Jonny ist auch nicht schlecht!");
    	  	   list.add(2, "in ya face!");
    	  	   list.add(3, category);

    	  	   return list.get(CategoryNumber);

    	     }
       
   }
}