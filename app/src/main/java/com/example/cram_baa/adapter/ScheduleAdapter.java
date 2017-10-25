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

public class ScheduleAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> list;

    public ScheduleAdapter(Context context, ArrayList<HashMap<String, String>> list) {
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
            convertView = inflater.inflate(R.layout.list_schedule_item, null);
            viewHold.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHold.tv_pvp = (TextView) convertView.findViewById(R.id.tv_pvp);
            viewHold.tv_month = (TextView) convertView.findViewById(R.id.tv_month);
            viewHold.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            viewHold.tv_hour = (TextView) convertView.findViewById(R.id.tv_hour);
            viewHold.tv_teacher = (TextView) convertView.findViewById(R.id.tv_teacher);
            viewHold.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }

        viewHold.tv_title.setText(list.get(position).get("content1"));
        viewHold.tv_pvp.setText(list.get(position).get("content2"));
        viewHold.tv_month.setText(list.get(position).get("content3"));
        viewHold.tv_time.setText(list.get(position).get("content4"));
        viewHold.tv_hour.setText(list.get(position).get("content5"));
        viewHold.tv_teacher.setText(list.get(position).get("content6"));
        viewHold.tv_address.setText(list.get(position).get("content7"));
        return convertView;
    }

    public static class ViewHold {
        TextView tv_title,tv_pvp,tv_month,tv_time,tv_hour,tv_teacher,tv_address;
    }
}
