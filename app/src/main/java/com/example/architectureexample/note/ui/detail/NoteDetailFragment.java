package com.example.architectureexample.note.ui.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.commonsware.cwac.richedit.RichEditText;
import com.example.architectureexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NoteDetailFragment extends Fragment {
    private static final String LOG_TAG = NoteDetailFragment.class.getSimpleName();

    public static final String EXTRA_TITLE = "com.example.architectureexample.note.ui.detail.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.architectureexample.note.ui.detail.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.architectureexample.note.ui.detail.EXTRA_PRIORITY";

    protected Unbinder unbinder;

    @BindView(R.id.edit_text_title)
    EditText editTextTitle;

    @BindView(R.id.edit_text_description)
    EditText editTextDescription;

   /* @BindView(R.id.rich_edit_text_content)
    RichEditText richEditTextContent;*/

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
        View view = inflater.inflate(R.layout.fragment_note_detail, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Indicate that this fragment needs to add option items to the toolbar
        setHasOptionsMenu(true);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.add_note_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();

        if(title.trim().isEmpty()){
            Toast.makeText(getActivity(), "Please insert a title", Toast.LENGTH_SHORT).show();
            editTextTitle.requestFocus();
            return;
        }

        if(description.trim().isEmpty()){
            Toast.makeText(getActivity(), "Please insert a description", Toast.LENGTH_SHORT).show();
            editTextDescription.requestFocus();
            return;
        }

        Intent data = new Intent();

        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        getActivity().setResult(Activity.RESULT_OK, data);
        getActivity().finish();
    }
}
