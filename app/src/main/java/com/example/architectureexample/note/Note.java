package com.example.architectureexample.note;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.example.architectureexample.common.db.AbstractEntity;
import com.example.architectureexample.user.User;

import java.io.Serializable;
import java.util.Date;

@Entity(
        tableName = "note",
        indices = {@Index(value = "uuid", unique = true), @Index("title"), @Index("user_id"), @Index("created_by_user_id"), @Index("updated_by_user_id")},
        foreignKeys = {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "id",
                        childColumns = "user_id",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "id",
                        childColumns = "created_by_user_id"
                ),
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "id",
                        childColumns = "updated_by_user_id"
                )
        }
)
public class Note extends AbstractEntity implements Serializable {

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "priority")
    private int priority;

    @ColumnInfo(name = "user_id")
    private Long userId;

    public Note(String title, String description, int priority, Long userId,
                @NonNull String uuid, @NonNull Date dateCreated, @NonNull Date dateUpdated,
                Long createdByUserId, Long updatedByUserId) {
        super(uuid, dateCreated, dateUpdated, createdByUserId, updatedByUserId);
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}