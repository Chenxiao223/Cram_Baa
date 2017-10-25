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

public class SelectionAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> list;

    public SelectionAdapter(Context context, ArrayList<HashMap<String, String>> list) {
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
            convertView = inflater.inflate(R.layout.list_selection_item, null);
            viewHold.tv_course = (TextView) convertView.findViewById(R.id.tv_course);
            viewHold.tv_introduce = (TextView) convertView.findViewById(R.id.tv_introduce);
            viewHold.tv_teacher = (TextView) convertView.findViewById(R.id.tv_teacher);
            viewHold.tv_rmb = (TextView) convertView.findViewById(R.id.tv_rmb);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }

        viewHold.tv_course.setText(list.get(position).get("content1"));
        viewHold.tv_introduce.setText(list.get(position).get("content2"));
        viewHold.tv_teacher.setText(list.get(position).get("content3"));
        viewHold.tv_rmb.setText(list.get(position).get("content4"));
        return convertView;
    }


    public static class ViewHold {
        TextView tv_course,tv_introduce,tv_teacher,tv_rmb;
    }
}
