package assign.project.learnyourfruits;


import java.util.ArrayList;
import java.util.List;

import android.R.id;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Main_menu extends Activity implements OnClickListener {

	Intent fruit_details;
	TextView[] tv=new TextView[21];
	ArrayList<Integer> fruit_list = new ArrayList<Integer>();
	
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
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/JandaCloserToFree.ttf");		
	
		txt_title.setTypeface(tf);
		
		for (int i=0; i<21;i++)
		{
			
			tv[i].setTypeface(tf);	
			tv[i].setOnClickListener(this);
		}
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
		
		
		
		
		
		
		for(int i=0;i<2;i++)
		{
			if (v.getId() == fruit_list.get(i)) {
				fruit_details = new Intent(getApplicationContext(),
						Fruit_Detail.class);
				fruit_details.putExtra("fruit_id", String.valueOf(i));				
				fruit_details.putExtra("fruit_name", tv[i].getText().toString());
				startActivity(fruit_details);			
			}			
		}
		
		
		
		
		
		
	}

}
