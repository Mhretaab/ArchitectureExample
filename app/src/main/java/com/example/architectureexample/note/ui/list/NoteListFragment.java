package com.example.architectureexample.note.ui.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.architectureexample.R;
import com.example.architectureexample.note.Note;
import com.example.architectureexample.note.NoteAdapter;
import com.example.architectureexample.note.NoteViewModel;

import java.util.List;
import java.util.UUID;

public class NoteListFragment extends Fragment {
    private static final String LOG_TAG = NoteListFragment.class.getSimpleName();

    protected Unbinder unbinder;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private NoteViewModel noteViewModel;

    public NoteListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_note_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        noteViewModel.getAllNotes().observe(this, (@Nullable List<Note> notes)->{
            adapter.setNotes(notes);
        });

        noteViewModel.findByUuid(UUID.randomUUID().toString()).observe(this, (@NonNull Note note)->{

        });
    }

    @OnClick(R.id.button_add_note)
    public void addNote(View view){

    }
}
