package com.example.bt_29_3_2024;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BeefAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Beef> beefList;

    public BeefAdapter(Context context, int layout, List<Beef> beefList) {
        this.context = context;
        this.layout = layout;
        this.beefList = beefList;
    }

    private class ViewHolder{
        TextView textView;
        ImageView imageView;
    }

    @Override
    public int getCount() {
        return beefList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.imageView = (ImageView) convertView.findViewById(R.id.imgBeef);
            holder.textView = (TextView) convertView.findViewById(R.id.name);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        Beef beef = beefList.get(position);
        holder.textView.setText(beef.getName());
        holder.imageView.setImageResource(beef.getImg());

        return convertView;
    }
}
