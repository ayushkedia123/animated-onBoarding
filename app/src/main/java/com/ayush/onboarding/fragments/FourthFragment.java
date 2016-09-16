package com.ayush.onboarding.fragments;


import android.annotation.TargetApi;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ayush.onboarding.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourthFragment extends Fragment {


    private View rootView;
    private ImageView buyButton;
    private ImageView dressInsidePhone;
    private ImageView buyerSmallImage;
    private Animation zoomOutDressAnimation;
    private Animation zoomInBuyerAnimation;
    private ImageView buyerBigImage;
    private ImageView thanksImageView;
    private ImageView youAreWelcomeImageView;
    private RelativeLayout textsAndButtonRelativeLayout;
    private Animation fadeOutRelativeLayoutAnimation;
    private Animation zoomInThanksAnimation;
    private Animation zoomInWelcomeAnimation;
    private Animation zoomOutThanksAnimation;
    private Animation zoomOutWelcomeAnimation;
    private Animation zoomOutBuyerAnimation;
    private Animation fadeInRelativeLayoutAnimation;
    private Animation zoomInDressAnimation;
    private Animation scaleUpShareAnimation;
    private Animation scaleDownShareAnimation;
    private AnimationDrawable buyersmileAnimation;
    private ImageView buyerSmileStar;

    public static FourthFragment newInstance() {
        FourthFragment fragmentFourth = new FourthFragment();
        return fragmentFourth;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_fourth, container, false);
        initUI();

        /* buy button animation will start after 5s (as per the requirement in video) */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                buyButton.startAnimation(scaleDownShareAnimation);
            }
        }, 1000);

        initListeners();
        return rootView;
    }

    /* Initialising UI and animations */
    private void initUI() {
        buyButton = (ImageView) rootView.findViewById(R.id.iv_btn_buy);
        dressInsidePhone = (ImageView) rootView.findViewById(R.id.iv_dress_inside_phone_screen_4);
        buyerSmallImage = (ImageView) rootView.findViewById(R.id.iv_buyer_image);
        buyerBigImage = (ImageView) rootView.findViewById(R.id.iv_buyer_big_image);
        thanksImageView = (ImageView) rootView.findViewById(R.id.iv_thanks);
        buyerSmileStar = (ImageView) rootView.findViewById(R.id.iv_buyer_smile_star);
        youAreWelcomeImageView = (ImageView) rootView.findViewById(R.id.tv_you_are_welcome);
        textsAndButtonRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.rl_text_button_screen_4);
        scaleUpShareAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_up);
        scaleDownShareAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_down);
        zoomInBuyerAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in_buyer);
        zoomOutDressAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_out_dress);
        zoomInDressAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in_dress);
        fadeOutRelativeLayoutAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
        fadeInRelativeLayoutAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        zoomInThanksAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in_thanks);
        zoomInWelcomeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in_welcome);
        zoomOutThanksAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_out_thanks);
        zoomOutWelcomeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_out_welcome);
        zoomOutBuyerAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_out_buyer);
    }

    /* listeners of different animations in which animations are starting on the animation end of other listeners  */
    private void initListeners() {
        scaleDownShareAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                buyButton.startAnimation(scaleUpShareAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        scaleUpShareAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dressInsidePhone.startAnimation(zoomOutDressAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        zoomOutDressAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onAnimationEnd(Animation animation) {
                buyerBigImage.startAnimation(zoomInBuyerAnimation);
                buyerSmallImage.setVisibility(View.INVISIBLE);
                buyerBigImage.setVisibility(View.VISIBLE);
                buyerBigImage.setBackground(getContext().getResources().getDrawable(R.drawable.buyer_smile_animation));
                buyersmileAnimation = (AnimationDrawable) buyerBigImage.getBackground();
                buyersmileAnimation.start();
                textsAndButtonRelativeLayout.startAnimation(fadeOutRelativeLayoutAnimation);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        buyerSmileStar.setVisibility(View.VISIBLE);
                    }
                }, 1000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeOutRelativeLayoutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                textsAndButtonRelativeLayout.setVisibility(View.GONE);
                thanksImageView.startAnimation(zoomInThanksAnimation);
                thanksImageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        zoomInThanksAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                buyerSmileStar.setVisibility(View.GONE);
                youAreWelcomeImageView.startAnimation(zoomInWelcomeAnimation);
                youAreWelcomeImageView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        zoomInWelcomeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        youAreWelcomeImageView.startAnimation(zoomOutWelcomeAnimation);
                        youAreWelcomeImageView.setVisibility(View.GONE);
                    }
                }, 1000);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        zoomOutWelcomeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                thanksImageView.startAnimation(zoomOutThanksAnimation);
                thanksImageView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        zoomOutThanksAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                buyerBigImage.startAnimation(zoomOutBuyerAnimation);
                buyerBigImage.setVisibility(View.GONE);
                textsAndButtonRelativeLayout.startAnimation(fadeInRelativeLayoutAnimation);
                textsAndButtonRelativeLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        zoomOutBuyerAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                buyerSmallImage.setVisibility(View.VISIBLE);
                dressInsidePhone.setAnimation(zoomInDressAnimation);
                dressInsidePhone.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
