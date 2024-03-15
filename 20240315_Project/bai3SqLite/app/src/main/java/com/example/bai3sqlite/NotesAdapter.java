package com.example.bai3sqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NotesAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<NotesModel> notesList;

    public NotesAdapter(MainActivity context, int layout, List<NotesModel> notesList) {
        this.context = context;
        this.layout = layout;
        this.notesList = notesList;
    }

    public NotesAdapter() {
    }

    @Override
    public int getCount() {
        return notesList.size();
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
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                                        .inflate(layout, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        NotesModel task = notesList.get(position);
        viewHolder.textViewNote.setText(task.getNameNote());

        viewHolder.imageViewEdit.setOnClickListener(v -> {
            context.updateDialog(task.getNameNote(), task.getIdNote());
        });

        viewHolder.imageViewDelete.setOnClickListener(v -> {
            context.deleteTask(task.getIdNote());
        });

        return convertView;
    }

    private class ViewHolder {
        TextView textViewNote;
        ImageView imageViewDelete, imageViewEdit;

        public ViewHolder(View view) {
            this.textViewNote = view.findViewById(R.id.textView_task);
            this.imageViewDelete = view.findViewById(R.id.image_delete);
            this.imageViewEdit = view.findViewById(R.id.image_note);
        }
    }
}


