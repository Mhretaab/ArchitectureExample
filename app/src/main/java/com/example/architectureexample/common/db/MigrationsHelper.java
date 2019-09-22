package com.example.architectureexample.common.db;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public final class MigrationsHelper {

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE note_table RENAME TO note");

            database.execSQL("CREATE TABLE `user` (`first_name` TEXT, `last_name` TEXT, `email` TEXT, `password` TEXT, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `uuid` TEXT NOT NULL, `date_created` INTEGER NOT NULL, `date_updated` INTEGER NOT NULL, `created_by_user_id` INTEGER, `updated_by_user_id` INTEGER, `street` TEXT, `state` TEXT, `city` TEXT, `post_code` INTEGER, FOREIGN KEY(`created_by_user_id`) REFERENCES `user`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`updated_by_user_id`) REFERENCES `user`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION )");

            database.execSQL("ALTER TABLE note ADD COLUMN date_created LONG");
            database.execSQL("ALTER TABLE note ADD COLUMN date_updated LONG");
            database.execSQL("ALTER TABLE note ADD COLUMN created_by_user_id LONG REFERENCES user(id)");
            database.execSQL("ALTER TABLE note ADD COLUMN updated_by_user_id LONG REFERENCES user(id)");

            database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_note_created_by_user_id ON note (created_by_user_id)");
            database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_note_updated_by_user_id ON note (updated_by_user_id)");
            database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_user_created_by_user_id ON user (created_by_user_id)");
            database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_user_updated_by_user_id ON user (updated_by_user_id)");
        }
    };

    public static final Migration[] ALL_MIGRATIONS = new Migration[]{MIGRATION_1_2};
}
