package com.example.bt1_22_3;

import android.media.MediaDrm;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private int VIEW_TYPE_ITEM = 0;
    private int VIEW_TYPE_LOADING = 1;

    public List<String> itemList;

    public ItemAdapter(List<String> item){
        this.itemList = item;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_ITEM){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
            return new ItemViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progressbar, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof ItemViewHolder){
                populateItemRows((ItemViewHolder) holder, position);
            }else if(holder instanceof LoadingViewHolder){
                showLoadingView((LoadingViewHolder) holder, position);
            }
    }

    @Override
    public int getItemCount(){
        if(itemList != null){
            return itemList.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if(itemList.get(position) == null){
            return VIEW_TYPE_LOADING;
        }else {
            return VIEW_TYPE_ITEM;
        }
    }

    private void showLoadingView(LoadingViewHolder holder, int position){

    }

    private void populateItemRows(ItemViewHolder holder, int position){
        String item = itemList.get(position);
        holder.tvItem.setText(item);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView tvItem;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item);
        }

    }
    public class LoadingViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressbar);
        }
    }
}
