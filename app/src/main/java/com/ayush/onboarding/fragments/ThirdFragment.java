package com.ayush.onboarding.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ayush.onboarding.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {


    private Animation scaleDownShareAnimation;
    private ImageView shareButton;
    private ImageView fbButton;
    private ImageView instagramButton;
    private ImageView twitterButton;
    private ImageView whatsappButton;
    private View rootView;
    private Animation zoomInAnimation;
    private ImageView sharedView;
    private Animation scaleUpShareAnimation;

    public static ThirdFragment newInstance() {
        ThirdFragment fragmentThird = new ThirdFragment();
        return fragmentThird;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_third, container, false);
        setSharedView(rootView);
        initUI();
        scaleDownShareAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_down);
        scaleUpShareAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_up);
        zoomInAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in);
        initListeners();
        return rootView;
    }

    private void setSharedView(View rootView) {
        sharedView = (ImageView) rootView.findViewById(R.id.iv_dress_inside_phone_screen_3);
    }

    public ImageView getSharedView() {
        return sharedView;
    }

    private void initUI() {
        shareButton = (ImageView) rootView.findViewById(R.id.iv_btn_share);
        fbButton = (ImageView) rootView.findViewById(R.id.iv_fb);
        twitterButton = (ImageView) rootView.findViewById(R.id.iv_twitter);
        instagramButton = (ImageView) rootView.findViewById(R.id.iv_instagram);
        whatsappButton = (ImageView) rootView.findViewById(R.id.iv_whatsapp);
    }

    private void initListeners() {

        scaleDownShareAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                fbButton.startAnimation(zoomInAnimation);
                fbButton.setVisibility(View.VISIBLE);
                whatsappButton.startAnimation(zoomInAnimation);
                whatsappButton.setVisibility(View.VISIBLE);
                twitterButton.startAnimation(zoomInAnimation);
                twitterButton.setVisibility(View.VISIBLE);
                instagramButton.startAnimation(zoomInAnimation);
                instagramButton.setVisibility(View.VISIBLE);
                shareButton.startAnimation(scaleUpShareAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void updateShareIconVisibility() {
        fbButton.setVisibility(View.VISIBLE);
        whatsappButton.setVisibility(View.VISIBLE);
        twitterButton.setVisibility(View.VISIBLE);
        instagramButton.setVisibility(View.VISIBLE);
    }

    /* Share button animation to give the feel that it is clicked by the user */
    public void onSharedTransitionEnd() {
        shareButton.startAnimation(scaleDownShareAnimation);
    }

}
