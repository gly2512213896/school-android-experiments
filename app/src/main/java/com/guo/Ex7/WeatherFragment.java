package com.guo.Ex7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.guo.R;

public class WeatherFragment extends Fragment {

    private static final String IMG_ID = "imgId";
    private static final String TEXT = "text";

    private int fragImgId;
    private String fragText;

    public WeatherFragment() {
        // Required empty public constructor
    }

    public static WeatherFragment newInstance(int imgId,String text) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putInt(IMG_ID, imgId);
        args.putString(TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragImgId = getArguments().getInt(IMG_ID);
            fragText = getArguments().getString(TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_weather, container, false);

        ImageView imageView=view.findViewById(R.id.iv_cl);
        TextView textView=view.findViewById(R.id.tv_cl);

        imageView.setImageResource(fragImgId);
        textView.setText(fragText);
        return view;
    }
}