package com.example.architectureexample.note;

import android.app.Application;
import android.os.AsyncTask;

import com.example.architectureexample.common.AppDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;
 
    public NoteRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }
 
    public Completable insert(final Note note) {
        return Completable.create((CompletableEmitter emitter)->{
            try {
                noteDao.insert(note);
                if(!emitter.isDisposed())
                    emitter.onComplete();
            }catch (Throwable throwable){
                if(!emitter.isDisposed())
                    emitter.onError(throwable);
            }
        });
    }
 
    public Completable update(final Note note) {
        return Completable.create((CompletableEmitter emitter)->{
            try {
                noteDao.update(note);
                if(!emitter.isDisposed())
                    emitter.onComplete();
            }catch (Throwable throwable){
                if(!emitter.isDisposed())
                    emitter.onError(throwable);
            }
        });
    }
 
    public Completable delete(final Note note) {
        return Completable.create((CompletableEmitter emitter)->{
            try {
                noteDao.delete(note);
                if(!emitter.isDisposed())
                    emitter.onComplete();
            }catch (Throwable throwable){
                if(!emitter.isDisposed())
                    emitter.onError(throwable);
            }
        });
    }
 
    public Completable deleteAllNotes() {
        return Completable.create((CompletableEmitter emitter)->{
            try {
                noteDao.deleteAllNotes();
                if(!emitter.isDisposed())
                    emitter.onComplete();
            }catch (Throwable throwable){
                if(!emitter.isDisposed())
                    emitter.onError(throwable);
            }
        });
    }

    public LiveData<Note> findByUuid(String uuid){
        return noteDao.findByUuid(uuid);
    }
 
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}