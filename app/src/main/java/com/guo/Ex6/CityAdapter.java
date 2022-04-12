package com.guo.Ex6;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.guo.R;

import java.util.List;

public class CityAdapter extends BaseAdapter {
    // 要填充到item里的数据集
    private List<City> cityList;
    // 要进行适配的页面
    private Context context;

    public CityAdapter(List<City> cityList, Context context) {
        this.cityList = cityList;
        this.context = context;
    }
    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public Object getItem(int i) {
        return cityList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.i("下标为", "getView: "+i);
        // 优化空间：
        // 1. 每次view重新出现在屏幕中的时候都要重新加载，改为为null时才加载
        // 2. 每次把item放到当前view中的时候都要重新绑定子view，改为用viewHolder把子view保存起来，因为虽然item不同，但是每个item的子view是一样的，
        //      也就是说item的view需要每次加载，但是item的子view可以不用每次加载
        ViewHolder viewHolder;
        if (view == null) {
            // from参数：是从哪个页面来的view；
            // inflate参数：要加载哪个view的布局文件；它的父布局是什么（如view之于viewGroup）；是否立马加到父布局中，
            view = LayoutInflater.from(context).inflate(R.layout.citylistitem, viewGroup, false);
            viewHolder = new ViewHolder();
            // viewHolder拿到的就是当前view的子view，其他view也有各自的子view
            viewHolder.item_cityName = view.findViewById(R.id.item_cityName);
            // tag可以看成view的额外空间，里面有啥view就是啥
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        // 把子view的属性设置一下，下次加载还是这些属性
        viewHolder.item_cityName.setText(cityList.get(i).getCityName());
        viewHolder.item_cityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,Ex6SubActivity.class));
            }
        });
        return view;
    }
    static class ViewHolder {
        Button item_cityName;
    }
}
