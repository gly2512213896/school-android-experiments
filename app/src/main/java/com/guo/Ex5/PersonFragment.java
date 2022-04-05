package com.guo.Ex5;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.guo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonFragment extends Fragment {
    private static final String fragName = "person fragment";
    // 自定义一个该fragment的上下文接口，只要所在的activity实现了这个接口，就可以重写方法在
    //   createView的时候修改Fragment的属性
    private MyFragmentCallBack myFragmentCallBack;

    // 用于给外界传参
    private static final String ARG_TV_TEXT = "tvText";
    private static final String ARG_BTN_TEXT = "btnText";
    private static final String ARG_IMG_SOURCE = "imgSrc";
    // fragment自己的参数
    private String tvText;
    private String btnText;
    private int imgSrc;

    public PersonFragment() {
        // Required empty public constructor
    }

    public String getFragName() {
        return this.fragName;
    }

    public static PersonFragment newInstance(String tvText, String btnText, int imgSrc) {
        // 创建一个PersonFragment
        PersonFragment personFragment = new PersonFragment();
        // 将要传递给Activity的参数放入Bundle中(Bundle是一个字符串为key,其他任意类型为value的类）
        // 给每个类型的value都提供了相应的添加接口
        Bundle args = new Bundle();
        args.putString(ARG_TV_TEXT, tvText);
        args.putString(ARG_BTN_TEXT, btnText);
        args.putInt(ARG_IMG_SOURCE, imgSrc);
        // 为fragment设置相应的参数
        personFragment.setArguments(args);
        return personFragment;
    }

    // 自定义的上下文首先拿到本来就有的上下文，从而让实现了自定义接口的activity类
    // 能够设置fragment的参数
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myFragmentCallBack = (MyFragmentCallBack) context;
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
    // 此处view是fragment的view，在activity中还得手动显示
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 自定义接口拿到所属activity的上下文用于设置标题
        MyFragmentCallBack myFragmentCallBack = (MyFragmentCallBack) getActivity();
        myFragmentCallBack.setData("Ex5");

        // 1. activity的xml文件中只需要有个布局就行了，用作container，也就是说activity的xml文件不能添加组件，否则会重复
        // 2. inflater要填充的是要用到的fragment自己的view.xml文件，而不是activity的；
        // 3. 填充fragment的view时再把整个view加入activity（container）中去
        // 4. 所有fragment公用同一个view的xml文件（此处用person的）
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        TextView textView = view.findViewById(R.id.tv_fragment);
        Button button = view.findViewById(R.id.btn_fragment);
        ImageView imageView = view.findViewById(R.id.iv_fragment);

        textView.setText(this.tvText);
        button.setText(this.btnText);
        imageView.setImageResource(this.imgSrc);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.btn_fragment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("current class", this.getClass().toString());
                // 自定义接口拿到自己的上下文用于传参
                MyFragmentCallBack myFragmentCallback = (MyFragmentCallBack) view.getContext();
                myFragmentCallBack.setData("设置了标题:" + PersonFragment.fragName);
            }
        });
    }

}