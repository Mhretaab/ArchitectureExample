package com.example.architectureexample.common.db;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.architectureexample.address.Address;
import com.example.architectureexample.common.db.converters.DateConverter;
import com.example.architectureexample.common.db.converters.ListConverter;
import com.example.architectureexample.note.Note;
import com.example.architectureexample.note.NoteDao;
import com.example.architectureexample.note.NoteDetail;
import com.example.architectureexample.user.User;
import com.example.architectureexample.user.UserDao;

import java.util.Date;
import java.util.UUID;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Database(entities = {Note.class, User.class}, views = {NoteDetail.class}, version = AppDatabase.DB_VERSION, exportSchema = false)
@TypeConverters({DateConverter.class, ListConverter.NoteConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static final String LOG_TAG = AppDatabase.class.getSimpleName();

    public static final int DB_VERSION = 2;
    public static final String DB_NAME = "app_database.db";

    private static AppDatabase instance;

    public abstract NoteDao noteDao();
    public abstract UserDao userDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addMigrations(MigrationsHelper.migrations)
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            Address address = new Address();
            address.city = "Addis Ababa";
            address.state = "Addis Ababa";
            address.street = "Hayahule";
            address.postCode = 120584;

            User user = new User("Mhret", "Berhe", "mhret@email.com", "mhret@123", address,
                    UUID.randomUUID().toString(), new Date(), new Date(), null, null);

            instance.userDao().insert(user)
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
                public void onSuccess(Long userId) {
                    Date dateCreated = new Date();
                    Note n1 = new Note("Title 1", "Description 1", 1, userId, UUID.randomUUID().toString(), dateCreated, dateCreated, userId, userId);
                    Note n2 = new Note("Title 2", "Description 2", 2, userId, UUID.randomUUID().toString(), dateCreated, dateCreated, userId, userId);
                    Note n3 = new Note("Title 3", "Description 3", 3, userId, UUID.randomUUID().toString(), dateCreated, dateCreated, userId, userId);

                    instance.noteDao().insertAll(new Note[]{n1, n2, n3})
                            .subscribeOn(Schedulers.io())
                            .subscribe(new CompletableObserver() {
                                @Override
                                public void onSubscribe(Disposable d) {}
                                @Override
                                public void onComplete() {}
                                @Override
                                public void onError(Throwable e) {}
                            });
                }
            });
        }
    };

}