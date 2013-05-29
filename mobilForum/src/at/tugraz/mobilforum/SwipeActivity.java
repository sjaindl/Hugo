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

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
         
        	return new ListViewFragment().setData("segment "+i);
//        	switch (i) {
//            
//            //hole von der DB die anzahl der Kategorien
//            //schleife#############################
//            //schicke eine anfrage an die datebank und gib alle einträge an der[i] stelle zurück
//            //schreibe alle einträge der Kategorie i in Tab i.
//            
//                case 0:
//                    // The first section of the app is the most interesting -- it offers
//                    // a launchpad into the other demonstrations in this example application.
//                
//                    return new ListViewFragment();
//                case 1:
//                	return  new ListViewFragment1();
//                case 2:
//                	return new ListViewFragment1();
//                case 3:
//                	return new ListViewFragment2();
//                case 4:
//                	return new ListViewFragment3();
//                	
//                default:
//                	return new ListViewFragment(null);
//            }
        }
//Jonny: getCount - soll den Wert aus der DB bekommen
        @Override
        public int getCount() {
            return 5;
        }
//Jonny: get PageTitle: soll den Namen der Category aus der DB bekommen
        @Override
        public CharSequence getPageTitle(int position) {
            return "Section " + (position + 1);
        }
    }

    
    
    public static class ListViewFragment extends Fragment {
   	 
    	String data;

    	public ListViewFragment setData(String data){
    		this.data = data;
    		return this;
    	}
    	
       @Override
       public View onCreateView(LayoutInflater inflater, ViewGroup container,
               Bundle savedInstanceState) {
           View rootView = inflater.inflate(R.layout.fragment_topic_list_activity_new_dummy, container, false);
           Bundle args = getArguments();
           //DO STUFF HERE
          ListView listview = (ListView) rootView.findViewById(R.id.listblabla);

           ArrayList<String> list = new ArrayList<String>();
           ArrayAdapter<String> adapter;
           adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, list);
          
           for(int i = 0; i < 4; i++){
          
        	   list.add(i, getDatafromDB(i));
        	   
           }
          listview.setAdapter(adapter);

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
    	  	   list.add(3, data);

    	  	   return list.get(CategoryNumber);

    	     }
       
   }
    
//    
//    public static class ListViewFragment1 extends Fragment {
//      	 
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_topic_list_activity_new_dummy, container, false);
//            Bundle args = getArguments();
//            //DO STUFF HERE
//           ListView listview = (ListView) rootView.findViewById(R.id.listblabla);
//3
//            ArrayList<String> list = new ArrayList<String>();
//            ArrayAdapter<String> adapter;
//            adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, list);
//           
//            for(int i = 0; i < 3; i++){
//           
//         	   //list.add(i, getDatafromDB(i));
//         	   
//            }
//            list.add("Coding is not a Crime!!!");
//            list.add("HalloBallo!");
//            
//           listview.setAdapter(adapter);
//
//           return rootView;
//        }
//        
//        
//        public  String getDatafromDB(int CategoryNumber){
//     	  	  //databasedummy
//     	  	   //i ... kategorien
//     	  	   ArrayList<String> list = new ArrayList<String>();
//     	  	   ArrayList<String> fromDB = new ArrayList<String>();
//     	  	   list.add(0, "David ist der Beste!");
//     	  	   list.add(1, "Jonny ist auch nicht schlecht!");
//     	  	   list.add(2, "in ya face!");
//
//     	  	   return list.get(CategoryNumber);
//
//     	     }
//        
//    }
//    
//    public static class ListViewFragment2 extends Fragment {
//     	 
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_topic_list_activity_new_dummy, container, false);
//            Bundle args = getArguments();
//            //DO STUFF HERE
//           ListView listview = (ListView) rootView.findViewById(R.id.listblabla);
//
//            ArrayList<String> list = new ArrayList<String>();
//            ArrayAdapter<String> adapter;
//            adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, list);
//           ListViewFragment
//            for(int i = 0; i < 3; i++){
//           
//         	   //list.add(i, getDatafromDB(i));
//         	   
//            }
//            list.add("go home...");
//            
//            
//           listview.setAdapter(adapter);
//
//           return rootView;
//        }
//        
//        
//        public  String getDatafromDB(int CategoryNumber){
//     	  	  //databasedummy
//     	  	   //i ... kategorien
//     	  	   ArrayList<String> list = new ArrayList<String>();
//     	  	   ArrayList<String> fromDB = new ArrayList<String>();
//     	  	   list.add(0, "David ist der Beste!");
//     	  	   list.add(1, "Jonny ist auch nicht schlecht!");
//     	  	   list.add(2, "in ya face!");
//
//     	  	   return list.get(CategoryNumber);
//
//     	     }
//        
//    }ListViewFragment
//    public static class ListViewFragment3 extends Fragment {
//    	 
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_topic_list_activity_new_dummy, container, false);
//            Bundle args = getArguments();
//            //DO STUFF HERE
//           ListView listview = (ListView) rootView.findViewById(R.id.listblabla);
//
//            ArrayList<String> list = new ArrayList<String>();
//            ArrayAdapter<String> adapter;
//            adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, list);
//           
//            for(int i = 0; i < 3; i++){
//           
//         	   //list.add(i, getDatafromDB(i));
//         	   
//            }
//            list.add("you're drunk!");
//            
//            
//           listview.setAdapter(adapter);
//
//           return rootView;
//        }
//        
//        
//        public  String getDatafromDB(int CategoryNumber){
//     	  	  //databasedummy
//     	  	   //i ... kategorien
//     	  	   ArrayList<String> list = new ArrayList<String>();
//     	  	   ArrayList<String> fromDB = new ArrayList<String>();
//     	  	   list.add(0, "David ist der Beste!");
//     	  	   list.add(1, "Jonny ist auch nicht schlecht!");
//     	  	   list.add(2, "in ya face!");
//
//     	  	   return list.get(CategoryNumber);
//
//     	     }
//        
//    }
}