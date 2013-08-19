package assign.project.learnyourfruits;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class Fruit_Detail extends Activity {

	 private ImageView selectedImageView;

	    private ImageView leftArrowImageView;

	    private ImageView rightArrowImageView;

	    private Gallery gallery;

	    private int selectedImagePosition = 0;

	    private List<Drawable> drawables;

	    private GalleryImageAdapter galImageAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fruit__detail);
		
		Bundle fruit_details = getIntent().getExtras();
		
		TextView cur_fruit_name = (TextView) findViewById(R.id.txt_cur_fruit_name);		
		
		String fruit_id = fruit_details.getString("fruit_id");	
		String fruit_name = fruit_details.getString("fruit_name");	
		
		cur_fruit_name.setText(fruit_name.toString());
		
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/JandaCloserToFree.ttf");		
		
		cur_fruit_name.setTypeface(tf);
		
		
		getDrawablesList(fruit_id);
        setupUI();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fruit__detail, menu);
		return true;
	}
	
	
	private void setupUI() {

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
	
	
	 private void changeBorderForSelectedImage(int selectedItemPos) {

	        int count = gallery.getChildCount();

	        for (int i = 0; i < count; i++) {

	            ImageView imageView = (ImageView) gallery.getChildAt(i);
	            imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.image_border));
	            imageView.setPadding(3, 3, 3, 3);

	        }

	        ImageView imageView = (ImageView) gallery.getSelectedView();
	        imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.selected_image_border));
	        imageView.setPadding(3, 3, 3, 3);
	    }
	
	 private void setSelectedImage(int selectedImagePosition) {

	        BitmapDrawable bd = (BitmapDrawable) drawables.get(selectedImagePosition);
	        Bitmap b = Bitmap.createScaledBitmap(bd.getBitmap(), (int) (bd.getIntrinsicHeight() * 0.9), (int) (bd.getIntrinsicWidth() * 0.7), false);
	        selectedImageView.setImageBitmap(b);
	        selectedImageView.setScaleType(ScaleType.FIT_XY);

	    }
	
	  private void getDrawablesList(String id) {

	        drawables = new ArrayList<Drawable>();
	        
	        if (id.equals("0"))
	        {
		        drawables.add(getResources().getDrawable(R.drawable.apple1));
		        drawables.add(getResources().getDrawable(R.drawable.apple2));
		        drawables.add(getResources().getDrawable(R.drawable.apple3));
		        drawables.add(getResources().getDrawable(R.drawable.apple4));
	        }
	        else if(id.equals("1"))
	        {
	        	drawables.add(getResources().getDrawable(R.drawable.avacado1));
		        drawables.add(getResources().getDrawable(R.drawable.avacado2));
		        drawables.add(getResources().getDrawable(R.drawable.avacado3));		        
	        }
	        

	    }

}
