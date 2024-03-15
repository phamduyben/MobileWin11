package com.example.bai1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bai1.databinding.ItemListUserBinding;

import java.util.List;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.MyViewHolder> {
    private final List<User> userList;
    private OnItemClickListener onItemClickListener;

    public ListUserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType
    ) {
        // Inflate the item layout and create a new MyViewHolder instance
        ItemListUserBinding itemListUserBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_list_user,
                parent,
                false
        );
        return new MyViewHolder(itemListUserBinding);
    }

    @Override
    public void onBindViewHolder(
            @NonNull MyViewHolder holder, int position
    ) {
        // Bind data to the ViewHolder
        holder.setBinding(userList.get(position), position);
    }

    @Override
    public int getItemCount() {
        // Return the size of the user list
        return userList.size();
    }

    public void setOnItemClickListener(
            OnItemClickListener onItemClickListener
    ) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void ItemClick(User user);
    }

    // Bind method to bind user data to the layout
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemListUserBinding itemListUserBinding;
        public ObservableField<String> stt = new ObservableField<>();
        public ObservableField<String> firstName = new ObservableField<>();
        public ObservableField<String> lastName = new ObservableField<>();
        private OnItemClickListener onItemClickListener;
        private User user;

        public MyViewHolder(ItemListUserBinding itemView) {
            super(itemView.getRoot());
            this.itemListUserBinding = itemView;
        }

        public void setBinding(User user, int position) {
            if (itemListUserBinding.getViewHolder() == null) {
                itemListUserBinding.setViewHolder(this);
            }
            this.user = user;
            stt.set(String.valueOf(position));
            firstName.set(user.getFirstName());
            lastName.set(user.getLastName());
        }

        public void onClick(View view) {
            this.onItemClickListener.ItemClick(user);
        }
    }
}


