package com.example.bt_29_3_2024.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bt_29_3_2024.R;
import com.example.bt_29_3_2024.activities.DetailActivity;
import com.example.bt_29_3_2024.models.SubCategory;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder> {
    private Context mContext;
    private List<SubCategory> subCategories;

    public SubCategoryAdapter(Context context, List<SubCategory> subCategories) {
        this.mContext = context;
        this.subCategories = subCategories;
    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType
    ) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.item_sub_category, parent, false);
        return new SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull SubCategoryViewHolder holder, int position
    ) {
        SubCategory subCategory = subCategories.get(position);

        Glide.with(mContext)
             .load(subCategory.getStrMealThumb())
             .into(holder.imgCategory);

        holder.tvCategory.setText(subCategory.getStrMeal());

        // redirect to detail activity
        holder.itemView.setOnClickListener(v -> {
            RedirectHelper.redirect(
                    mContext,
                    DetailActivity.class,
                    "id",
                    Integer.parseInt(subCategory.getIdMeal())
            );
        });
    }

    @Override
    public int getItemCount() {
        if (subCategories != null) {
            return subCategories.size();
        }
        return 0;
    }

    public class SubCategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        CircleImageView imgCategory;

        public SubCategoryViewHolder(
                @NonNull View itemView
        ) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tv_category);
            imgCategory = itemView.findViewById(R.id.img_category);
        }
    }
}
