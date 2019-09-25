package com.example.architectureexample.note.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.architectureexample.R;
import com.example.architectureexample.note.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> notes = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);

        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.tvTitle.setText(currentNote.getTitle());
        holder.tvDescription.setText(currentNote.getDescription());
        holder.tvPriority.setText(String.valueOf(currentNote.getPriority()));
        holder.tvDateCreated.setText(new SimpleDateFormat("YYYY-MM-dd").format(currentNote.getDateCreated()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public Note getNoteAt(int position){
        return notes.get(position);
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle, tvDescription, tvPriority, tvDateCreated;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.text_view_title);
            tvDescription = itemView.findViewById(R.id.text_view_description);
            tvPriority = itemView.findViewById(R.id.text_view_priority);
            tvDateCreated = itemView.findViewById(R.id.text_view_date_created);

            itemView.setOnClickListener((view) -> {
                int position = getAdapterPosition();

                if(listener != null && position != RecyclerView.NO_POSITION){
                    listener.onItemClick(notes.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
