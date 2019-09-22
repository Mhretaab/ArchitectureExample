package com.example.architectureexample.note;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.architectureexample.common.db.AppDatabase;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NoteRepository {
    private static final String LOG_TAG = NoteRepository.class.getSimpleName();

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;
 
    public NoteRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }
 
    public void insert(final Note note) {
        noteDao.insert(note)
                .subscribeOn(Schedulers.io())
                /*.observeOn(AndroidSchedulers.mainThread())*/
                .subscribe(new SingleObserver<Long>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {}
                    @Override
                    public void onError(Throwable e) {
                        Log.e(LOG_TAG, e.getLocalizedMessage(), e);
                    }
                    @Override
                    public void onSuccess(Long noteId) {}
                });
    }
 
    public void update(final Note note) {
        noteDao.update(note)
                .subscribeOn(Schedulers.io())
                /*.observeOn(AndroidSchedulers.mainThread())*/
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {}
                    @Override
                    public void onError(Throwable e) {
                        Log.e(LOG_TAG, e.getLocalizedMessage(), e);
                    }
                    @Override
                    public void onSuccess(Integer numberOfUpdatedNotes) {}
                });
    }
 
    public void delete(final Note note) {
        noteDao.delete(note)
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
 
    public void deleteAllNotes() {
        noteDao.deleteAllNotes()
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public Single<Note> findByUuid(String uuid){
        return noteDao.findByUuid(uuid);
    }
 
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}