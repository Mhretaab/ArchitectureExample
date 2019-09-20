package com.example.architectureexample.note;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "note_table",
        indices = {@Index(value = "uuid", unique = true), @Index("title")}
)
public class Note {
 
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "priority")
    private int priority;

    @ColumnInfo(name = "uuid")
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