package com.example.architectureexample.note;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {
 
    @PrimaryKey(autoGenerate = true)
    private int id;
 
    private String title;
 
    private String description;
 
    private int priority;

    private String uuid;
 
    public Note(String title, String description, int priority, String uuid) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.uuid = uuid;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public int getId() {
        return id;
    }
 
    public String getTitle() {
        return title;
    }
 
    public String getDescription() {
        return description;
    }
 
    public int getPriority() {
        return priority;
    }

    public String getUuid() {
        return uuid;
    }
}