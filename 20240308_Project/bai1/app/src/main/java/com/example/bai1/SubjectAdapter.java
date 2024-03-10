package com.example.bai1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SubjectAdapter extends BaseAdapter {
    //khai báo
    private final Context context;
    private final int layout;
    private final List<Subject> monHocList;

    public SubjectAdapter(Context context, int layout, List<Subject> monHocList) {
        this.context = context;
        this.layout = layout;
        this.monHocList = monHocList;
    }

    @Override
    public int getCount() {
        return monHocList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        //lấy context
//        LayoutInflater inflater = (LayoutInflater)
//                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        //gọi view chứa layout
//        convertView = inflater.inflate(layout, null);
//        //ánh xạ view
//        TextView textName = convertView.findViewById(R.id.textName);
//        TextView textDesc = convertView.findViewById(R.id.textDesc);
//        ImageView imagePic = convertView.findViewById(R.id.imagePic);
//        //gán giá trị
//        Subject monHoc = monHocList.get(position);
//        textName.setText(monHoc.getName());
//        textDesc.setText(monHoc.getDesc());
//        imagePic.setImageResource(monHoc.getPic());
//        //trả về view
//        return convertView;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //khởi tạo viewholder
        ViewHolder viewHolder;
        //lấy context
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //gọi view chứa layout
            convertView = inflater.inflate(layout, null);
            //ánh xạ view
            viewHolder = new ViewHolder();
            viewHolder.textName = convertView.findViewById(R.id.textName_grid);
            viewHolder.textDesc = convertView.findViewById(R.id.textDesc_grid);
            viewHolder.imagePic = convertView.findViewById(R.id.imagePic_grid);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //gán giá trị
        Subject monHoc = monHocList.get(position);
        viewHolder.textName.setText(monHoc.getName());
        viewHolder.textDesc.setText(monHoc.getDesc());
        viewHolder.imagePic.setImageResource(monHoc.getPic());
        //trả về view
        return convertView;
    }

    private class ViewHolder {
        TextView textName, textDesc;
        ImageView imagePic;
    }

}
