package com.skynet.notes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    interface NoteClickListener {
        void NoteClick(Note note, int position);
        boolean NoteLongClick(Note note, int position);
    }

    private final NoteClickListener onClickListener;

    private final LayoutInflater inflater;
    private final List<Note> notes;

    NoteAdapter(Context context, List<Note> notes, NoteClickListener onClickListener) {
        this.notes = notes;
        this.inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
    }
    @Override
    @NonNull
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.notes_style, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Note note = notes.get(position);
        holder.titleView.setText(note.getTitle());
        holder.dataView.setText(note.getDate());
        if (note.getImgFile() == null) {
            holder.noteImgView.setImageResource(R.drawable.no_image);
        }

        holder.itemView.setOnClickListener(v -> onClickListener.NoteClick(note, position));
        holder.itemView.setOnLongClickListener(v -> onClickListener.NoteLongClick(note, position));
     }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {// implements View.OnCreateContextMenuListener {
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