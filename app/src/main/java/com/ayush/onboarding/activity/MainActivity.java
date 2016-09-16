package com.ayush.onboarding.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ayush.onboarding.R;
import com.ayush.onboarding.fragments.FirstFragment;
import com.ayush.onboarding.fragments.FourthFragment;
import com.ayush.onboarding.fragments.SecondFragment;
import com.ayush.onboarding.fragments.ThirdFragment;
import com.ayush.onboarding.utils.OnSwipeTouchListener;

public class MainActivity extends AppCompatActivity {

    private FirstFragment firstFragment;
    private int count = 0;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    private FourthFragment fourthFragment;
    private Transition transition;
    private View conatiner;
    private ImageView getStartedButton;
    private ImageView dotIndicator1;
    private ImageView dotIndicator2;
    private ImageView dotIndicator3;
    private ImageView dotIndicator4;
    private Animation getStartedAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initListeners();
        firstFragment = FirstFragment.newInstance();
        dotIndicator1.setImageResource(R.drawable.shape_big_dot);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, firstFragment)
                .commit();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void initUI() {
        conatiner = findViewById(R.id.container);
        transition = TransitionInflater.from(MainActivity.this).inflateTransition(R.transition.change_image_trans);
        getStartedButton = (ImageView) findViewById(R.id.iv_get_started);
        getStartedAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_in_get_started);
        dotIndicator1 = (ImageView) findViewById(R.id.iv_dot_1);
        dotIndicator2 = (ImageView) findViewById(R.id.iv_dot_2);
        dotIndicator3 = (ImageView) findViewById(R.id.iv_dot_3);
        dotIndicator4 = (ImageView) findViewById(R.id.iv_dot_4);

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void initListeners() {

        /**
         * listener is added so that when transition will complete from screen 2 to 3
         * then another animation will be start in share button of 3rd screen
         **/
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                if (thirdFragment != null)
                    thirdFragment.onSharedTransitionEnd();

            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });

        /* changing page by gesture listener */
        conatiner.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
            }

            public void onSwipeRight() {
                if (count == 0)
                    return;
                count--;
                getSupportFragmentManager().popBackStack();
                updatePageIndicator(count);
            }

            public void onSwipeLeft() {
                if (count == 3)
                    return;
                count++;
                changePage(count);
            }

            public void onSwipeBottom() {
            }
        });
    }

    /* To update page indicators on swipe and back pressed */
    private void updatePageIndicator(int count) {
        if (count == 2) {
            thirdFragment.updateShareIconVisibility();
            dotIndicator1.setImageResource(R.drawable.shape_small_dot);
            dotIndicator2.setImageResource(R.drawable.shape_small_dot);
            dotIndicator3.setImageResource(R.drawable.shape_big_dot);
            dotIndicator4.setImageResource(R.drawable.shape_small_dot);
        } else if (count == 1) {
            dotIndicator1.setImageResource(R.drawable.shape_small_dot);
            dotIndicator2.setImageResource(R.drawable.shape_big_dot);
            dotIndicator3.setImageResource(R.drawable.shape_small_dot);
            dotIndicator4.setImageResource(R.drawable.shape_small_dot);
        } else if (count == 0) {
            dotIndicator1.setImageResource(R.drawable.shape_big_dot);
            dotIndicator2.setImageResource(R.drawable.shape_small_dot);
            dotIndicator3.setImageResource(R.drawable.shape_small_dot);
            dotIndicator4.setImageResource(R.drawable.shape_small_dot);
        }

    }

    /* To change page on swipe */
    private void changePage(int count) {
        if (count == 1) {
            dotIndicator1.setImageResource(R.drawable.shape_small_dot);
            dotIndicator2.setImageResource(R.drawable.shape_big_dot);
            dotIndicator3.setImageResource(R.drawable.shape_small_dot);
            dotIndicator4.setImageResource(R.drawable.shape_small_dot);
            secondFragment = SecondFragment.newInstance();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                firstFragment.setSharedElementReturnTransition(TransitionInflater.from(MainActivity.this).inflateTransition(R.transition.change_image_trans));
                firstFragment.setExitTransition(TransitionInflater.from(MainActivity.this).inflateTransition(android.R.transition.fade));
                secondFragment.setSharedElementEnterTransition(TransitionInflater.from(MainActivity.this).inflateTransition(R.transition.change_image_trans));
                secondFragment.setEnterTransition(TransitionInflater.from(MainActivity.this).inflateTransition(android.R.transition.fade));
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .addSharedElement(firstFragment.getSharedView(), getResources().getString(R.string.transition_dress))
                    .replace(R.id.container, secondFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (count == 2) {
            dotIndicator1.setImageResource(R.drawable.shape_small_dot);
            dotIndicator2.setImageResource(R.drawable.shape_small_dot);
            dotIndicator3.setImageResource(R.drawable.shape_big_dot);
            dotIndicator4.setImageResource(R.drawable.shape_small_dot);
            thirdFragment = ThirdFragment.newInstance();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                secondFragment.setSharedElementReturnTransition(TransitionInflater.from(MainActivity.this).inflateTransition(R.transition.change_image_trans));
                secondFragment.setExitTransition(TransitionInflater.from(MainActivity.this).inflateTransition(android.R.transition.fade));
                thirdFragment.setSharedElementEnterTransition(transition);
                thirdFragment.setEnterTransition(TransitionInflater.from(MainActivity.this).inflateTransition(android.R.transition.fade));
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .addSharedElement(secondFragment.getSharedView(), getResources().getString(R.string.transition_dress))
                    .replace(R.id.container, thirdFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (count == 3) {
            dotIndicator1.setImageResource(R.drawable.shape_small_dot);
            dotIndicator2.setImageResource(R.drawable.shape_small_dot);
            dotIndicator3.setImageResource(R.drawable.shape_small_dot);
            dotIndicator4.setImageResource(R.drawable.shape_big_dot);
            getStartedButton.startAnimation(getStartedAnimation);
            getStartedButton.setVisibility(View.VISIBLE);
            fourthFragment = FourthFragment.newInstance();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                thirdFragment.setSharedElementReturnTransition(TransitionInflater.from(MainActivity.this).inflateTransition(R.transition.change_image_trans));
                thirdFragment.setExitTransition(TransitionInflater.from(MainActivity.this).inflateTransition(android.R.transition.fade));
                fourthFragment.setSharedElementEnterTransition(TransitionInflater.from(MainActivity.this).inflateTransition(R.transition.change_image_trans));
                fourthFragment.setEnterTransition(TransitionInflater.from(MainActivity.this).inflateTransition(android.R.transition.fade));
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .addSharedElement(thirdFragment.getSharedView(), getResources().getString(R.string.transition_dress))
                    .replace(R.id.container, fourthFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    /* updating page indicator and count on back pressed*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        count--;
        updatePageIndicator(count);
    }
}
