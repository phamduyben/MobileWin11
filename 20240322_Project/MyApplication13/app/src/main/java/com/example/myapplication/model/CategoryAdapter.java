package com.example.myapplication.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private Context context;
    private List<Category> array;
    private OnCategoryClickListener listener;

    // Constructor để truyền context và danh sách category
    public CategoryAdapter(Context context, List<Category> array) {
        this.context = context;
        this.array = array;
    }

    // Interface để lắng nghe sự kiện click trên mỗi item
    public interface OnCategoryClickListener {
        void onCategoryClick(Category category);
    }

    // Phương thức để thiết lập listener
    public void setOnCategoryClickListener(OnCategoryClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = array.get(position);
        holder.tenSp.setText(category.getName());
        Glide.with(context)
                .load(category.getImages())
                .into(holder.images);

        // Set click listener for category item
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCategoryClick(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return array == null ? 0 : array.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView images;
        public TextView tenSp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.image_cate);
            tenSp = itemView.findViewById(R.id.tvNameCategory);
        }
    }
}

