package com.guo.Ex5;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.guo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlantFragment extends Fragment {

    private MyFragmentCallBack myFragmentCallBack;
    private static final String fragName="plant fragment";
    // 用于给外界传参
    private static final String ARG_TV_TEXT = "tvText";
    private static final String ARG_BTN_TEXT = "btnText";
    private static final String ARG_IMG_SOURCE = "imgSrc";
    // fragment自己的参数
    private String tvText;
    private String btnText;
    private int imgSrc;

    public PlantFragment() {
        // Required empty public constructor
    }

    public static PlantFragment newInstance(String tvText, String btnText, int imgSrc) {
        // 创建一个PlantFragment
        PlantFragment plantFragment = new PlantFragment();
        // 将要传递给Activity的参数放入Bundle中(Bundle是一个字符串为key,其他任意类型为value的类）
        // 给每个类型的value都提供了相应的添加接口
        Bundle args = new Bundle();
        args.putString(ARG_TV_TEXT, tvText);
        args.putString(ARG_BTN_TEXT, btnText);
        args.putInt(ARG_IMG_SOURCE, imgSrc);
        // 为fragment设置相应的参数
        plantFragment.setArguments(args);
        return plantFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 创建碎片的时候拿到activity通过newInstance传给fragment的参数
        if (getArguments() != null) {
            this.tvText = this.getArguments().getString(ARG_TV_TEXT);
            this.btnText = this.getArguments().getString(ARG_BTN_TEXT);
            this.imgSrc = this.getArguments().getInt(ARG_IMG_SOURCE);
        }
    }

    // 猜测：先加载activity的xml文件，再执行fragment.java，再执行activity.java
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 1. activity的xml文件中只需要有个布局就行了，用作container，不能添加组件，否则会重复
        // 2. inflater要填充的是fragment自己的view.xml文件，而不是activity的；
        // 3. 填充fragment的view时再把整个view加入activity（container）中去
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        TextView textView = view.findViewById(R.id.tv_fragment);
        textView.setText(this.tvText);
        Button button = view.findViewById(R.id.btn_fragment);
        button.setText(this.btnText);
        ImageView imageView = view.findViewById(R.id.iv_fragment);
        imageView.setImageResource(this.imgSrc);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button= view.findViewById(R.id.btn_fragment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("current class", this.getClass().toString());
                // 自定义接口拿到所属activity的上下文用于传参
                MyFragmentCallBack myFragmentCallback= (MyFragmentCallBack) view.getContext();
                myFragmentCallback.setData("设置了标题:"+PlantFragment.fragName);
            }
        });
    }
}