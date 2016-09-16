package com.ayush.onboarding.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ayush.onboarding.R;
import com.ayush.onboarding.utils.Typewriter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {


    private Typewriter description;
    private Typewriter priceText;
    private ImageView sharedView;

    public static SecondFragment newInstance() {
        SecondFragment fragmentSecond = new SecondFragment();
        return fragmentSecond;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_second, container, false);
        setSharedView(rootView);
        description = (Typewriter) rootView.findViewById(R.id.tv_description);
        priceText = (Typewriter) rootView.findViewById(R.id.tv_price);
        description.setCharacterDelay(30);
        description.animateText(getActivity().getResources().getString(R.string.txt_empire_waist_dress));
        description.setCompletionListener(new Typewriter.OnComplete() {
            @Override
            public void completed() {
                priceText.setCharacterDelay(30);
                priceText.animateText("Rs. 3000");
            }
        });
        return rootView;
    }

    private void setSharedView(View rootView) {
        sharedView = (ImageView) rootView.findViewById(R.id.iv_dress_inside_phone_screen_2);
    }

    public ImageView getSharedView() {
        return sharedView;
    }

}
