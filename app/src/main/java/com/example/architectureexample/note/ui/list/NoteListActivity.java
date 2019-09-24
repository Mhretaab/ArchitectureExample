package com.example.architectureexample.note.ui.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.architectureexample.R;
import com.example.architectureexample.note.Note;
import com.example.architectureexample.note.ui.detail.NoteDetailFragment;

import java.util.Date;
import java.util.UUID;

public class NoteListActivity extends AppCompatActivity {
    private static final String LOG_TAG = NoteListActivity.class.getSimpleName();

    Fragment noteListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        setTitle("Notes");

        noteListFragment = new NoteListFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_note_list, noteListFragment);
        ft.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(noteListFragment != null)
            noteListFragment.onActivityResult(requestCode, resultCode, data);
    }
}
