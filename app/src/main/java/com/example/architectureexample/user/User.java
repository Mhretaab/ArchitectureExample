package com.example.architectureexample.user;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.example.architectureexample.address.Address;
import com.example.architectureexample.common.db.AbstractEntity;

import java.io.Serializable;
import java.util.Date;

@Entity(
        tableName = "user",
        indices = {@Index(value = "uuid", unique = true), @Index(value = "email", unique = true), @Index("created_by_user_id"), @Index("updated_by_user_id")},
        foreignKeys = {
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
public class User extends AbstractEntity  implements Serializable {
    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "password")
    private String password;

    @Embedded
    private Address address;

    public User(String firstName, String lastName, String email, String password, Address address,
                @NonNull String uuid, Date dateCreated, Date dateUpdated,
                Long createdByUserId, Long updatedByUserId) {
        super(uuid, dateCreated, dateUpdated, createdByUserId, updatedByUserId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password.trim();
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Address getAddress() {
        return address;
    }
}
