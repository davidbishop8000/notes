package com.skynet.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Note> notes;

    NoteAdapter(Context context, List<Note> notes) {
        this.notes = notes;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.notes_style, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteAdapter.ViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.titleView.setText(note.getTitle());
        holder.dataView.setText(note.getDate());
        if (note.getImgFile() == null) {
            holder.noteImgView.setImageResource(R.drawable.no_image);
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView titleView;
        final TextView dataView;
        final ImageView noteImgView;

        ViewHolder(View view){
            super(view);
            titleView = view.findViewById(R.id.title);
            dataView = view.findViewById(R.id.date);
            noteImgView = view.findViewById(R.id.img_note);
        }
    }
}