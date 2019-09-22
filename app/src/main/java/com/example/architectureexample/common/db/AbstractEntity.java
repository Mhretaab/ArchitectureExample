package com.example.architectureexample.common.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.architectureexample.common.db.converters.DateConverter;

import java.util.Date;
import java.util.Objects;

public abstract class AbstractEntity {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Long id;

    @NonNull
    @ColumnInfo(name = "uuid")
    private String uuid;

    @NonNull
    @TypeConverters({DateConverter.class})
    @ColumnInfo(name = "date_created")
    private Date dateCreated;

    @NonNull
    @TypeConverters({DateConverter.class})
    @ColumnInfo(name = "date_updated")
    private Date dateUpdated;

    @ColumnInfo(name = "created_by_user_id")
    private Long createdByUserId;

    @ColumnInfo(name = "updated_by_user_id")
    private Long updatedByUserId;

    public AbstractEntity() {
    }

    public AbstractEntity(@NonNull String uuid, @NonNull Date dateCreated, @NonNull Date dateUpdated, Long createdByUserId, Long updatedByUserId) {
        this.uuid = uuid;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.createdByUserId = createdByUserId;
        this.updatedByUserId = updatedByUserId;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    @NonNull
    public String getUuid() {
        return uuid;
    }

    public void setUuid(@NonNull String uuid) {
        this.uuid = uuid;
    }

    @NonNull
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(@NonNull Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @NonNull
    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(@NonNull Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Long getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Long createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public Long getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(Long updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
