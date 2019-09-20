package com.example.architectureexample.note.ui.detail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.architectureexample.R;

public class NoteDetailFragment extends Fragment {
    private static final String LOG_TAG = NoteDetailFragment.class.getSimpleName();

    protected Unbinder unbinder;

    @BindView(R.id.edit_text_title)
    EditText editTextTitle;

    @BindView(R.id.edit_text_description)
    EditText editTextDescription;

    @BindView(R.id.number_picker_priority)
    NumberPicker numberPickerPriority;

    public NoteDetailFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_note_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
