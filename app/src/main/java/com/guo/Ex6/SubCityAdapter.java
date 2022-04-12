package com.guo.Ex6;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.guo.R;

import java.util.List;

public class SubCityAdapter extends BaseAdapter {
    // 要填充到item里的数据集
    private List<SubCity> subCityList;

    public SubCityAdapter(List<SubCity> subCityList, Context context) {
        this.subCityList = subCityList;
        this.context = context;
    }

    // 要进行适配的页面
    private Context context;

    @Override
    public int getCount() {
        return subCityList.size();
    }

    @Override
    public Object getItem(int i) {
        return subCityList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.citygriditem, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.item_subCityName = view.findViewById(R.id.item_subCityName);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.item_subCityName.setText(subCityList.get(i).getSubCityName());
        viewHolder.item_subCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Ex6SubActivity.class));
            }
        });
        return view;
    }

    static class ViewHolder {
        Button item_subCityName;
    }
}

