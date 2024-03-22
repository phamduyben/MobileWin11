package com.example.bairetrofit.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bairetrofit.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private final List<Category> categoryList;
    private final Context context;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.item_category, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.categoryName.setText(category.getName());
        Glide.with(context)
             .load(category.getImages())
             .into(holder.categoryImage);
    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView categoryName;
        private final ImageView categoryImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.image_cate);
            categoryName = itemView.findViewById(R.id.tvNameCategory);
            categoryImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Do something
                    Toast.makeText(
                                 context,
                                 "You clicked on " + categoryName.getText(),
                                 Toast.LENGTH_SHORT
                         )
                         .show();
                }
            });
        }
    }
}
