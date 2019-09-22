package com.example.architectureexample.note;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.architectureexample.common.db.BaseDao;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface NoteDao extends BaseDao<Note> {
    @Query("DELETE FROM note")
    Completable deleteAllNotes();
 
    @Query("SELECT * FROM note ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM note WHERE uuid = :uuid")
    Single<Note> findByUuid(String uuid);

    @Query("SELECT * FROM note_detail")
    LiveData<List<NoteDetail>> findAllNoteDetail();
}