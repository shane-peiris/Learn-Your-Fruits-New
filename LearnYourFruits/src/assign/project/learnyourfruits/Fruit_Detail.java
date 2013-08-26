package assign.project.learnyourfruits;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ImageView.ScaleType;

public class Fruit_Detail extends Activity {

	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;

	private ViewFlipper vf;
	private Context mContext;
	private final GestureDetector detector = new GestureDetector(
			new MyGestureDetector());
	
	 
	 
	 private ImageView selectedImageView;

	    private ImageView leftArrowImageView;

	    private ImageView rightArrowImageView;

	    private Gallery gallery;

	    private int selectedImagePosition = 0;

	    private List<Drawable> drawables;

	    private GalleryImageAdapter galImageAdapter;
	    
	    ArrayList<String> fruit_img_name = new ArrayList<String>();
	    ArrayList<String> fruit_name = new ArrayList<String>();
	
	    public int fruit_id;
	
	    public TextView cur_fruit_name;
	    
	    public ImageView swip_r,swip_l,loading,loading_text;
	    
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fruit__detail);
		mContext = this;		
		init_array();
		
		
		
		
		
		Bundle fruit_details = getIntent().getExtras();
		
		cur_fruit_name = (TextView) findViewById(R.id.txt_cur_fruit_name);
		swip_r = (ImageView) findViewById(R.id.img_swip_r);
		swip_l = (ImageView) findViewById(R.id.img_swip_l);
		
		loading=(ImageView) findViewById(R.id.img_loading);
		loading.setImageBitmap(null);
		loading.setBackgroundResource( R.anim.animation_list );
		
		loading_text=(ImageView) findViewById(R.id.img_loading_text);
		
		swip_prv();
		
		
		
		
		//swip_r.setVisibility(View.INVISIBLE);
		//swip_l.setVisibility(View.INVISIBLE);
		
		fruit_id = Integer.parseInt(fruit_details.getString("fruit_id")) ;	
		String fruit_name = fruit_details.getString("fruit_name");	
		String fruit_img = fruit_details.getString("fruit_img");	
		
		cur_fruit_name.setText(fruit_name.toString());
		
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/JandaCloserToFree.ttf");		
		Typeface tf2 = Typeface.createFromAsset(getAssets(), "fonts/delte.ttf");
		
		cur_fruit_name.setTypeface(tf2);
		//cur_fruit_name.setTextSize(65);
		cur_fruit_name.setTextColor(Color.parseColor("#fec01d"));
		cur_fruit_name.setShadowLayer((float)7, (float)8, (float)5, Color.parseColor("#000000"));
		
		try
		{
			getDrawablesList(fruit_img);
	        setupUI();
		}
		catch(Exception ex)
		{}
		
		
		//View Flipper START
		
		vf = (ViewFlipper) this.findViewById(R.id.viewFlipper1);
		vf.setOnTouchListener(new OnTouchListener() {
	        @Override
	        public boolean onTouch(final View view, final MotionEvent event) {
	        	
	        	
	        	
	        	detector.onTouchEvent(event);
	        	 
	            return true;
	        }
	    });
		
		//View Flipper END
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fruit__detail, menu);
		return true;
	}
	
	
	private void setupUI() {

		try{
		
        selectedImageView = (ImageView) findViewById(R.id.selected_imageview);
        leftArrowImageView = (ImageView) findViewById(R.id.left_arrow_imageview);
        rightArrowImageView = (ImageView) findViewById(R.id.right_arrow_imageview);
        gallery = (Gallery) findViewById(R.id.gallery);

        leftArrowImageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (selectedImagePosition > 0) {
                    --selectedImagePosition;

                }

                gallery.setSelection(selectedImagePosition, false);
            }
        });

        rightArrowImageView.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                if (selectedImagePosition < drawables.size() - 1) {
                    ++selectedImagePosition;

                }

                gallery.setSelection(selectedImagePosition, false);

            }
        });

        gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                selectedImagePosition = pos;

                if (selectedImagePosition > 0 && selectedImagePosition < drawables.size() - 1) {

                    leftArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_left_enabled));
                    rightArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_right_enabled));

                } else if (selectedImagePosition == 0) {

                    leftArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_left_disabled));

                } else if (selectedImagePosition == drawables.size() - 1) {

                    rightArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_right_disabled));
                }

                changeBorderForSelectedImage(selectedImagePosition);
                setSelectedImage(selectedImagePosition);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        galImageAdapter = new GalleryImageAdapter(this, drawables);

        gallery.setAdapter(galImageAdapter);

        if (drawables.size() > 0) {

            gallery.setSelection(selectedImagePosition, false);

        }

        if (drawables.size() == 1) {

            rightArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_right_disabled));
        }
		}
		catch(Exception ex)
		{}

    }
	
	
	 private void changeBorderForSelectedImage(int selectedItemPos) {

	        int count = gallery.getChildCount();

	        for (int i = 0; i < count; i++) {

	            ImageView imageView = (ImageView) gallery.getChildAt(i);
	            //imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.image_border));
	            imageView.setPadding(3, 3, 3, 3);

	        }

	        ImageView imageView = (ImageView) gallery.getSelectedView();
	        //imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.selected_image_border));
	        imageView.setPadding(3, 3, 3, 3);
	    }
	
	 private void setSelectedImage(int selectedImagePosition) {

	        BitmapDrawable bd = (BitmapDrawable) drawables.get(selectedImagePosition);
	        Bitmap b = Bitmap.createScaledBitmap(bd.getBitmap(), (int) (bd.getIntrinsicHeight()), (int) (bd.getIntrinsicWidth()), false);
	        selectedImageView.setImageBitmap(b);
	        //selectedImageView.setScaleType(ScaleType.FIT_XY);

	    }
	 
	 public void swip_prv()
	 {
			 
		 //worker.shutdownNow();
		 
		 final ScheduledExecutorService worker = 
				  Executors.newSingleThreadScheduledExecutor();		 
		 
		 
		 Animation myFadeInAnimation = AnimationUtils.loadAnimation(Fruit_Detail.this, R.anim.blink);
			swip_r.startAnimation(myFadeInAnimation);
			//swip_l.startAnimation(myFadeInAnimation);
			
			
			  final Handler handler = new Handler();
		        handler.postDelayed(new Runnable() {
		          @Override
		          public void run() {
		        	  //Animation myFadeOutAnimation = AnimationUtils.loadAnimation(Fruit_Detail.this, R.anim.off);
				    	//swip_r.startAnimation(myFadeOutAnimation);
				    	Animation myFadeInAnimation = AnimationUtils.loadAnimation(Fruit_Detail.this, R.anim.blink);
				    	swip_l.startAnimation(myFadeInAnimation);
		            
		          }
		        }, 3000);	
			  
			  
			  
			  
			  
			  
		        /*final Handler handler2 = new Handler();
		        handler2.postDelayed(new Runnable() {
		          @Override
		          public void run() {
		        	  Animation myFadeOutAnimation = AnimationUtils.loadAnimation(Fruit_Detail.this, R.anim.off);
				    	swip_l.startAnimation(myFadeOutAnimation);
		            
		          }
		        }, 5000);*/
		        
		        
		        
			  
			  
			  /*Runnable task2 = new Runnable() {
				    public void run() {
				       Do something… 			    	
				    	Animation myFadeOutAnimation = AnimationUtils.loadAnimation(Fruit_Detail.this, R.anim.off);
				    	swip_l.startAnimation(myFadeOutAnimation);
				    }
				  };
				  
				  worker2.schedule(task2, 4, TimeUnit.SECONDS);*/	
				  
				  /*  Runnable task3 = new Runnable() {
					    public void run() {
					      Do something… 			    	
					    	try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					    }
					  };
			
					  worker3.schedule(task3, 5, TimeUnit.SECONDS);*/	
		        
		        
		 
		 
	 }
	
	  private void getDrawablesList(String id) {

	        drawables = new ArrayList<Drawable>();
	        
	        Resources res = getResources();
	        
	        String f_name = id.toLowerCase();
	        
	        
	        
	        //Drawable drawable = res.getDrawable(resID);
	        	try
		        {
	        		int resID1 = res.getIdentifier(f_name+"1", "drawable", getPackageName());
	        		drawables.add(getResources().getDrawable(resID1));
	    	        int resID2 = res.getIdentifier(f_name+"2", "drawable", getPackageName());
	    	        drawables.add(getResources().getDrawable(resID2));	
	    	        int resID3 = res.getIdentifier(f_name+"3", "drawable", getPackageName()); 
		        	drawables.add(getResources().getDrawable(resID3));
		        }
		        catch(Exception ex)
		        {
		        	
		        	
		        }

	    }
	  
	  
	  public void init_array()
		{
			fruit_name.add("Apple");
			fruit_name.add("Avacado");
			fruit_name.add("Banana");
			fruit_name.add("Blackberries");
			fruit_name.add("Cherries");
			fruit_name.add("Dates");
			fruit_name.add("Grape Fruit");
			fruit_name.add("Guava");
			fruit_name.add("Grapes");
			fruit_name.add("Kiwi Fruit");
			fruit_name.add("Lemon");
			fruit_name.add("Lime");
			fruit_name.add("Mangoe");
			fruit_name.add("Orange");
			fruit_name.add("Papaya");
			fruit_name.add("Peaches");
			fruit_name.add("Pears");
			fruit_name.add("Pineapple");
			fruit_name.add("Raspberries");
			fruit_name.add("Strawberries");
			fruit_name.add("Watermelon");	
			
			fruit_img_name.add("apple");
			fruit_img_name.add("avacado");
			fruit_img_name.add("banana");
			fruit_img_name.add("blackberries");
			fruit_img_name.add("cherries");
			fruit_img_name.add("dates");
			fruit_img_name.add("grapefruit");
			fruit_img_name.add("guava");
			fruit_img_name.add("grapes");
			fruit_img_name.add("kiwifruit");
			fruit_img_name.add("lemon");
			fruit_img_name.add("lime");
			fruit_img_name.add("mangoe");
			fruit_img_name.add("orange");
			fruit_img_name.add("papaya");
			fruit_img_name.add("peaches");
			fruit_img_name.add("pears");
			fruit_img_name.add("pineapple");
			fruit_img_name.add("raspberries");
			fruit_img_name.add("strawberries");
			fruit_img_name.add("watermelon");
			
			
			
			
		}
	  
	  class MyGestureDetector extends SimpleOnGestureListener {
			@SuppressWarnings("deprecation")
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
					float velocityY) {
				try {

					
					
					int resID1 =  getResources().getIdentifier("loading_"+Locale.getDefault().getLanguage().toString(), "drawable", getPackageName());
	        		
					loading_text.setImageResource(resID1);
					//loading_text.setBackgroundResource(resID1);
					
					
					// right to left swipe
					if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
							&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
						vf.setInAnimation(AnimationUtils.loadAnimation(mContext,
								R.anim.left_in));
						vf.setOutAnimation(AnimationUtils.loadAnimation(mContext,
								R.anim.left_out));
						//vf.showNext();
						
						//Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT);
						
						int temp_fruit_id=0;
						
						if(fruit_id+1 > 21)						
							fruit_id=0;						
						else
							fruit_id++;
						
						//Intent fruit_details = new Intent(getApplicationContext(),
						//		Fruit_Detail.class);
						//fruit_details.putExtra("fruit_id", String.valueOf(temp_fruit_id));
						//fruit_details.putExtra("fruit_img", fruit_img_name.get(temp_fruit_id));
						//fruit_details.putExtra("fruit_name", fruit_name.get(temp_fruit_id));
						//startActivity(fruit_details);	
						//Fruit_Detail.this.finish();
						
						
						loading.setVisibility(View.VISIBLE);
						loading_text.setVisibility(View.VISIBLE);
						
						
						
						final AnimationDrawable mailAnimation = (AnimationDrawable) loading.getBackground();
						loading.post(new Runnable() {
						    public void run() {
						        if ( mailAnimation != null ) mailAnimation.start();
						      }
						});
						
						
						try
						{	
							
							final Handler handler = new Handler();
					        handler.postDelayed(new Runnable() {
					          @Override
					          public void run() {
					        	  cur_fruit_name.setText(fruit_name.get(fruit_id));
									getDrawablesList(fruit_img_name.get(fruit_id));
							        setupUI();
							        
									//swip_r.startAnimation(null);
							        
							        swip_prv();
							        if ( mailAnimation != null ) mailAnimation.stop();
							        loading.setVisibility(View.INVISIBLE);
							        loading_text.setVisibility(View.INVISIBLE);
					            
					            
					          }
					        }, 7000);
							
							
							
							
						}
						catch(Exception ex)
						{}
						
						
						return true;
					} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
							&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
						vf.setInAnimation(AnimationUtils.loadAnimation(mContext,
								R.anim.right_in));
						vf.setOutAnimation(AnimationUtils.loadAnimation(mContext,
								R.anim.right_out));
						//vf.showPrevious();
						
						//Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT);
						
						int temp_fruit_id=0;
						
						if(fruit_id-1 < 0)
							fruit_id=21;
						else
							fruit_id--;
						
						//Intent fruit_details = new Intent(getApplicationContext(),
						//		Fruit_Detail.class);
						//fruit_details.putExtra("fruit_id", String.valueOf(temp_fruit_id));
						//fruit_details.putExtra("fruit_img", fruit_img_name.get(fruit_id+1));
						//fruit_details.putExtra("fruit_name", fruit_name.get(temp_fruit_id));
						//startActivity(fruit_details);	
						//Fruit_Detail.this.finish();
						
						loading.setVisibility(View.VISIBLE);
						loading_text.setVisibility(View.VISIBLE);
						
						final AnimationDrawable mailAnimation = (AnimationDrawable) loading.getBackground();
						loading.post(new Runnable() {
						    public void run() {
						        if ( mailAnimation != null ) mailAnimation.start();
						      }
						});
						
						try
						{	
							
							
							final Handler handler = new Handler();
					        handler.postDelayed(new Runnable() {
					          @Override
					          public void run() {
					        	  cur_fruit_name.setText(fruit_name.get(fruit_id));
									getDrawablesList(fruit_img_name.get(fruit_id));
							        setupUI();
							        
							        //swip_l.startAnimation(null);
									//swip_r.startAnimation(null);
							        swip_prv();
							        if ( mailAnimation != null ) mailAnimation.stop();
							        loading.setVisibility(View.INVISIBLE);
							        loading_text.setVisibility(View.INVISIBLE);
					            
					          }
					        }, 7000);
							
							
						}
						catch(Exception ex)
						{}
						
						return true;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				return false;
			}
		}

}
