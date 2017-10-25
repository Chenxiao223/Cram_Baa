package com.example.cram_baa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cram_baa.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MyClassMateAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> list;

    public MyClassMateAdapter(Context context, ArrayList<HashMap<String, String>> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold viewHold = null;
        if (convertView == null) {
            viewHold = new ViewHold();
            convertView = inflater.inflate(R.layout.list_my_class_mate, null);
            viewHold.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHold.tv_class = (TextView) convertView.findViewById(R.id.tv_class);
            viewHold.tv_telephoneNo = (TextView) convertView.findViewById(R.id.telephone_number);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }

        viewHold.tv_name.setText(list.get(position).get("content1"));
        viewHold.tv_class.setText(list.get(position).get("content2"));
        viewHold.tv_telephoneNo.setText(list.get(position).get("content3"));
        return convertView;
    }


    public static class ViewHold {
        TextView tv_name,tv_class,tv_telephoneNo;
    }
}
