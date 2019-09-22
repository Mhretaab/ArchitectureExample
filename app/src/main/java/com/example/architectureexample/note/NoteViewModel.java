package com.example.architectureexample.note;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Single;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository noteRepository;
    private LiveData<List<Note>> allNotes;
 
    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }
 
    public void insert(Note note) {
        noteRepository.insert(note);
    }
 
    public void update(Note note) {
        noteRepository.update(note);
    }
 
    public void delete(Note note) {
        noteRepository.delete(note);
    }
 
    public void deleteAllNotes() {
        noteRepository.deleteAllNotes();
    }

    public Single<Note> findByUuid(String uuid){
        return noteRepository.findByUuid(uuid);
    }
 
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}