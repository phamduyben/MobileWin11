package com.example.baitap2_29_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<User> users;
    private Context mContext;

    public UserAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<User> list){
        this.users= list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        if(user == null){
            return;
        }
        holder.tvUser.setText(user.getName());
        holder.imgAnh.setImageResource(user.getResourceIDAnh());
    }

    @Override
    public int getItemCount() {
        if(users != null){
            return users.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView tvUser;
        private ImageView  imgAnh;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnh = itemView.findViewById(R.id.img_anh);
            tvUser = itemView.findViewById(R.id.tv_user);
        }
    }
}
