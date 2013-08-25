package assign.project.learnyourfruits;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.R.id;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Main_menu extends Activity implements OnClickListener {

	Intent fruit_details;
	TextView[] tv=new TextView[21];
	ArrayList<Integer> fruit_list = new ArrayList<Integer>();
	ArrayList<String> fruit_name = new ArrayList<String>();
	
	 public ImageView swip_up,one_tap;
	 public LinearLayout m_full;
	 
	 private static final ScheduledExecutorService worker = 
			  Executors.newSingleThreadScheduledExecutor();
	 private static final ScheduledExecutorService worker2 = 
			  Executors.newSingleThreadScheduledExecutor();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		fruit_list.add(R.id.txt_f_name1);
		fruit_list.add(R.id.txt_f_name2);
		fruit_list.add(R.id.txt_f_name3);
		fruit_list.add(R.id.txt_f_name4);
		fruit_list.add(R.id.txt_f_name5);
		fruit_list.add(R.id.txt_f_name6);
		fruit_list.add(R.id.txt_f_name7);
		fruit_list.add(R.id.txt_f_name8);
		fruit_list.add(R.id.txt_f_name9);
		fruit_list.add(R.id.txt_f_name10);
		fruit_list.add(R.id.txt_f_name11);
		fruit_list.add(R.id.txt_f_name12);
		fruit_list.add(R.id.txt_f_name13);
		fruit_list.add(R.id.txt_f_name14);
		fruit_list.add(R.id.txt_f_name15);
		fruit_list.add(R.id.txt_f_name16);
		fruit_list.add(R.id.txt_f_name17);
		fruit_list.add(R.id.txt_f_name18);
		fruit_list.add(R.id.txt_f_name19);
		fruit_list.add(R.id.txt_f_name20);
		fruit_list.add(R.id.txt_f_name21);
		
		fruit_name.add("apple");
		fruit_name.add("avacado");
		fruit_name.add("banana");
		fruit_name.add("blackberries");
		fruit_name.add("cherries");
		fruit_name.add("dates");
		fruit_name.add("grapefruit");
		fruit_name.add("guava");
		fruit_name.add("grapes");
		fruit_name.add("kiwifruit");
		fruit_name.add("lemon");
		fruit_name.add("lime");
		fruit_name.add("mangoe");
		fruit_name.add("orange");
		fruit_name.add("papaya");
		fruit_name.add("peaches");
		fruit_name.add("pears");
		fruit_name.add("pineapple");
		fruit_name.add("raspberries");
		fruit_name.add("strawberries");
		fruit_name.add("watermelon");
		
		
		
		TextView txt_title = (TextView) findViewById(R.id.txt_select_title);
		
		
		for(int f=0;f<21;f++)
		{
			tv[f] = (TextView) findViewById(fruit_list.get(f));	
			
		}
		
		
		/*tv[0] = (TextView) findViewById(R.id.txt_f_name1);		
		tv[1] = (TextView) findViewById(R.id.txt_f_name2);
		tv[2] = (TextView) findViewById(R.id.txt_f_name3);
		tv[3] = (TextView) findViewById(R.id.txt_f_name4);
		tv[4] = (TextView) findViewById(R.id.txt_f_name5);
		tv[5] = (TextView) findViewById(R.id.txt_f_name6);
		tv[6] = (TextView) findViewById(R.id.txt_f_name7);
		tv[7] = (TextView) findViewById(R.id.txt_f_name8);
		tv[8] = (TextView) findViewById(R.id.txt_f_name9);
		tv[9] = (TextView) findViewById(R.id.txt_f_name10);
		tv[10] = (TextView) findViewById(R.id.txt_f_name11);
		tv[11] = (TextView) findViewById(R.id.txt_f_name12);
		tv[12] = (TextView) findViewById(R.id.txt_f_name13);
		tv[13] = (TextView) findViewById(R.id.txt_f_name14);
		tv[14] = (TextView) findViewById(R.id.txt_f_name15);
		tv[15] = (TextView) findViewById(R.id.txt_f_name16);
		tv[16] = (TextView) findViewById(R.id.txt_f_name17);
		tv[17] = (TextView) findViewById(R.id.txt_f_name18);
		tv[18] = (TextView) findViewById(R.id.txt_f_name19);
		tv[19] = (TextView) findViewById(R.id.txt_f_name20);
		tv[20] = (TextView) findViewById(R.id.txt_f_name21)*/
		
		//Font File		
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/mickey.TTF");
		Typeface tf2 = Typeface.createFromAsset(getAssets(), "fonts/Minnie.TTF");	
		Typeface tf3 = Typeface.createFromAsset(getAssets(), "fonts/delte.ttf");	
		Typeface tf4 = Typeface.createFromAsset(getAssets(), "fonts/spanky.TTF");	
	
		txt_title.setTypeface(tf2);
		txt_title.setTextSize(38);
		txt_title.setTextColor(Color.parseColor("#1a97cf"));
		txt_title.setShadowLayer((float)2, (float)9, (float)5, Color.parseColor("#ef864c"));
		//txt_title.setEms(20);
		
		for (int i=0; i<21;i++)
		{
			
			tv[i].setTypeface(tf3);	
			tv[i].setOnClickListener(this);
			tv[i].setTextColor(Color.parseColor("#b81d66"));
			tv[i].setTextSize(32);
			tv[i].setShadowLayer((float)2, (float)2, (float)5, Color.parseColor("#000000"));
		}
		
		m_full = (LinearLayout) findViewById(R.id.l_full);
		//m_full.setBackgroundColor(Color.parseColor("#000000"));
		
		
		swip_up = (ImageView) findViewById(R.id.img_swipe_up);
		one_tap = (ImageView) findViewById(R.id.img_one_tap);
		
		Animation myFadeInAnimation = AnimationUtils.loadAnimation(Main_menu.this, R.anim.blink2);
		
		one_tap.startAnimation(myFadeInAnimation);
		
		//Animation myFadeInAnimation3 = AnimationUtils.loadAnimation(Main_menu.this, R.anim.dim);
		//m_full.startAnimation(myFadeInAnimation3);
		
		
		
		
		
		
		
		Runnable task = new Runnable() {
		    public void run() {
		      /* Do something… */
		    	Animation myFadeInAnimation = AnimationUtils.loadAnimation(Main_menu.this, R.anim.blink2);
		    	swip_up.startAnimation(myFadeInAnimation);
		    	Animation myFadeOutAnimation = AnimationUtils.loadAnimation(Main_menu.this, R.anim.off);
		    	one_tap.startAnimation(myFadeOutAnimation);
		    	
		    }
		  };
		  worker.schedule(task, 4, TimeUnit.SECONDS);
		
		  
		  
		  
		  final Handler handler = new Handler();
	        handler.postDelayed(new Runnable() {
	          @Override
	          public void run() {
	        	  Animation myFadeOutAnimation = AnimationUtils.loadAnimation(Main_menu.this, R.anim.off);
		      		//Animation myFadeOutAnimation = AnimationUtils.loadAnimation(Fruit_Detail.this, R.anim.off);
		        	  swip_up.startAnimation(myFadeOutAnimation);
	            
	          }
	        }, 8000);
		  
		/*
		  Runnable task2 = new Runnable() {
			    public void run() {
			      // Do something… 
			    	 Animation myFadeOutAnimation = AnimationUtils.loadAnimation(Main_menu.this, R.anim.off);
			      		//Animation myFadeOutAnimation = AnimationUtils.loadAnimation(Fruit_Detail.this, R.anim.off);
			        	  swip_up.startAnimation(myFadeOutAnimation);
			    }
			  };
			  worker2.schedule(task2, 10, TimeUnit.SECONDS);
			*/
			  
			  
				
			  /*
			 final Handler handler = new Handler();
		        handler.postDelayed(new Runnable() {
		          @Override
		          public void run() {
		        	  Animation myFadeOutAnimation = AnimationUtils.loadAnimation(Main_menu.this, R.anim.off);
		      		//Animation myFadeOutAnimation = AnimationUtils.loadAnimation(Fruit_Detail.this, R.anim.off);
		        	  swip_up.startAnimation(myFadeOutAnimation);
		      		
		            
		            
		          }
		        }, 	4000);*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		
		
		
		
		for(int i=0;i<21;i++)
		{
			if (v.getId() == fruit_list.get(i)) {
				fruit_details = new Intent(getApplicationContext(),
						Fruit_Detail.class);
				fruit_details.putExtra("fruit_id", String.valueOf(i));
				fruit_details.putExtra("fruit_img", fruit_name.get(i));
				fruit_details.putExtra("fruit_name", tv[i].getText().toString());
				startActivity(fruit_details);	
				
			}			
		}
		
	}
	
	
	/*public void onStart(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Animation myFadeInAnimation = AnimationUtils.loadAnimation(Main_menu.this, R.anim.blink);
		
		one_tap.startAnimation(myFadeInAnimation);
		
		Runnable task = new Runnable() {
		    public void run() {
		       //Do something… 
		    	Animation myFadeInAnimation = AnimationUtils.loadAnimation(Main_menu.this, R.anim.blink);
		    	swip_up.startAnimation(myFadeInAnimation);
		    	Animation myFadeOutAnimation = AnimationUtils.loadAnimation(Main_menu.this, R.anim.off);
		    	one_tap.startAnimation(myFadeOutAnimation);
		    }
		  };
		  worker.schedule(task, 4, TimeUnit.SECONDS);
		
		
		  Runnable task2 = new Runnable() {
			    public void run() {
			      // Do something… 
			    	 Animation myFadeOutAnimation = AnimationUtils.loadAnimation(Main_menu.this, R.anim.off);
			      		//Animation myFadeOutAnimation = AnimationUtils.loadAnimation(Fruit_Detail.this, R.anim.off);
			        	  swip_up.startAnimation(myFadeOutAnimation);
			    }
			  };
			  worker.schedule(task2, 10, TimeUnit.SECONDS);
	}*/
	

}
