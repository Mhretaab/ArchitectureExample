package com.example.architectureexample.note.ui.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.architectureexample.R;
import com.example.architectureexample.note.Note;
import com.example.architectureexample.note.ui.detail.NoteDetailActivity;
import com.example.architectureexample.note.ui.detail.NoteDetailFragment;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class NoteListFragment extends Fragment {
    private static final String LOG_TAG = NoteListFragment.class.getSimpleName();

    public static final int ADD_NOTE_REQUEST = 1;

    protected Unbinder unbinder;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private NoteListViewModel noteListViewModel;

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
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        noteListViewModel = ViewModelProviders.of(this).get(NoteListViewModel.class);

        noteListViewModel.getAllNotes().observe(this, (@Nullable List<Note> notes) -> {
            adapter.setNotes(notes);
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteListViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Note deleted", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerView);

        /*noteListViewModel.findByUuid(UUID.randomUUID().toString()).observe(this, (@NonNull List<Note> notes)->{

        });*/
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.list_note_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_notes:
                deleteAllNotes();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void deleteAllNotes() {
        noteListViewModel.deleteAllNotes();
        Toast.makeText(getActivity(), "All notes deleted", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.button_add_note)
    public void addNote(View view) {
        Intent intent = new Intent(getActivity(), NoteDetailActivity.class);
        getActivity().startActivityForResult(intent, ADD_NOTE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            String title = data.getStringExtra(NoteDetailFragment.EXTRA_TITLE);
            String description = data.getStringExtra(NoteDetailFragment.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(NoteDetailFragment.EXTRA_PRIORITY, 1);

            Note note = new Note(title, description, priority, 1L, UUID.randomUUID().toString(), new Date(), new Date(), null, null);
            noteListViewModel.insert(note);

            Toast.makeText(getActivity(), "Note saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
