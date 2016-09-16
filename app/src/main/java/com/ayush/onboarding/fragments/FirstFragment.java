package com.ayush.onboarding.fragments;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ayush.onboarding.R;
import com.ayush.onboarding.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    private ImageView sharedView;
    private View cameraButton;
    private MainActivity activity;
    private static FirstFragment firstFragment;
    private ImageView cameraFlash;
    private ImageView cameraCorners;
    private AnimationDrawable flashAnimation;


    public static FirstFragment newInstance() {
        if (firstFragment == null) {
            firstFragment = new FirstFragment();
        }
        return firstFragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        cameraFlash = (ImageView) rootView.findViewById(R.id.iv_camera_flash);
        cameraCorners = (ImageView) rootView.findViewById(R.id.iv_camera_corners);
        setSharedView(rootView);

        /* camera flash animation will start after 1000ms so that it will appear smoothly */
        new Handler().postDelayed(new Runnable() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                cameraFlash.setBackground(activity.getResources().getDrawable(R.drawable.camera_flash_animation));
                flashAnimation = (AnimationDrawable) cameraFlash.getBackground();
                flashAnimation.start();
                updateCameraCorners();
            }
        }, 1000);

        return rootView;
    }

    /**
     * camera focus corners inside the phone screen will become invisible as the flash animation will complete.
     * As there is no callbacks to get end of AnimationDrawable so using handler to delay.
     */
    private void updateCameraCorners() {
        new Handler().postDelayed(new Runnable() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                cameraCorners.setVisibility(View.INVISIBLE);
            }
        }, 700);
    }

    private void setSharedView(View rootView) {
        sharedView = (ImageView) rootView.findViewById(R.id.iv_dress_inside_phone);
    }

    public ImageView getSharedView() {
        return sharedView;
    }

}
