package com.example.architectureexample.note;

import androidx.room.DatabaseView;

@DatabaseView(
        value = "SELECT note.title, note.description, note.priority, user.first_name || user.last_name AS owner FROM note " +
        "LEFT OUTER JOIN user ON user.id=note.user_id",
        viewName = "note_detail"
)
public class NoteDetail {
    public String title;
    public String description;
    public String priority;
    public String owner;
}
