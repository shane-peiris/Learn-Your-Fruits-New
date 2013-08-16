package assign.project.learnyourfruits;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.widget.TextView;

public class Main_menu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		
		TextView tv1 = (TextView) findViewById(R.id.txt_select_title);
		
		TextView[] tv=new TextView[21];
		
		tv[0] = (TextView) findViewById(R.id.txt_f_name1);
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
		tv[20] = (TextView) findViewById(R.id.txt_f_name21);
		
		
		
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/comic.ttf");		
	
		tv1.setTypeface(tf);
		
		for (int i=0; i<21;i++)
		{
			tv[i].setTypeface(tf);			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

}
