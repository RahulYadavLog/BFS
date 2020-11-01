package com.example.pdfview.activity;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.example.pdfview.AddDetailActivity1;
import com.example.pdfview.R;
import com.example.pdfview.ViewFlipperIndicator;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,GestureDetector.OnGestureListener {

    static final float END_SCALE=0.7f;
    ImageView drawerImg;
    LinearLayout bill_gen;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RelativeLayout contentView;
    ViewFlipper viewFlipper;
    private ViewFlipperIndicator flipper;
    private Animation lInAnim;
    private Animation lOutAnim;

    private String KEY_FLIPPER_POSITION = "flipper_position";

    private GestureDetector detector = null;

    private Handler myHandler = new Handler();

    // flipper restarts flipping
    private Runnable flipController = new Runnable() {
        @Override
        public void run() {
            flipper.setInAnimation(lInAnim);
            flipper.setOutAnimation(lOutAnim);
            flipper.showNext();
            flipper.startFlipping();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
*/

        setContentView(R.layout.activity_home);
        drawerImg=findViewById(R.id.drawer_Img);
        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.navigation);
        contentView=findViewById(R.id.home_layout);
        bill_gen=findViewById(R.id.bill_gen);

        lInAnim = AnimationUtils.loadAnimation(this, R.anim.push_left_in);
        lOutAnim = AnimationUtils.loadAnimation(this, R.anim.push_left_out);

        flipper = (ViewFlipperIndicator) findViewById(R.id.viewFlipper);

        // set values radius and margin for view flipper indicators
        flipper.setRadius(10);
        flipper.setMargin(10);

        // set colors for the indicators
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(android.R.color.black));
        flipper.setPaintCurrent(paint);

        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.colorAccent2));
        flipper.setPaintNormal(paint);

        flipper.setInAnimation(lInAnim);
        flipper.setOutAnimation(lOutAnim);
        flipper.setAutoStart(true);
        flipper.setFlipInterval(3000);
        bill_gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, AddDetailActivity1.class);
                startActivity(intent);
            }
        });

        // flipper has a previous position. we should restore it
        if (savedInstanceState != null) {
            flipper.setDisplayedChild(savedInstanceState.getInt(KEY_FLIPPER_POSITION));
        }

        flipper.startFlipping();

        detector = new GestureDetector(this, this);

        int gallery_grid_Images[]={R.drawable.logo2,R.drawable.logo2,R.drawable.logo2,R.drawable.logo2,R.drawable.logo2,
                R.drawable.logo2,R.drawable.logo2,R.drawable.logo2,

        };
       /* for(int i=0;i<gallery_grid_Images.length;i++)
        {
            //  This will create dynamic image view and add them to ViewFlipper
            setFlipperImage(gallery_grid_Images[i]);
        }*/
        navigationDrawer();
    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);
        drawerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }


            }
        });
        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorAccent));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
           /* case R.id.cat:
                Intent intent=new Intent(getApplicationContext(),AllCategoriesActivity.class);
                startActivity(intent);
                break;*/
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

   /* private void setFlipperImage(int res) {
        Log.i("Set Filpper Called", res+"");
        ImageView image = new ImageView(getApplicationContext());
        image.setBackgroundResource(res);
        viewFlipper.addView(image);
    }
*/
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_FLIPPER_POSITION, flipper.getDisplayedChild());
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        // you can change this value
        float sensitivity = 50;

        if ((e1.getX() - e2.getX()) > sensitivity) {

            flipper.showNext();

            // first stops flipping
            flipper.stopFlipping();

            // then flipper restarts flipping in 3 seconds
            myHandler.postDelayed(flipController, 3000);

            return true;
        } else if ((e2.getX() - e1.getX()) > sensitivity) {
            Animation rInAnim = AnimationUtils.loadAnimation(this, R.anim.push_right_in);
            Animation rOutAnim = AnimationUtils.loadAnimation(this, R.anim.push_right_out);
            flipper.setInAnimation(rInAnim);
            flipper.setOutAnimation(rOutAnim);
            flipper.showPrevious();

            // first stops flipping
            flipper.stopFlipping();

            // then flipper restarts flipping in 3 seconds
            myHandler.postDelayed(flipController, 3000);

            return true;
        }

        return true;
    }
}