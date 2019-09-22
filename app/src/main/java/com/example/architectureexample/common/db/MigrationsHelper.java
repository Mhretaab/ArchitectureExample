package com.example.architectureexample.common.db;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public final class MigrationsHelper {

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE note ADD COLUMN created_by_user_id LONG REFERENCES user(id)");
            database.execSQL("ALTER TABLE note ADD COLUMN updated_by_user_id LONG REFERENCES user(id)");
            database.execSQL("ALTER TABLE user ADD COLUMN created_by_user_id LONG REFERENCES user(id)");
            database.execSQL("ALTER TABLE user ADD COLUMN updated_by_user_id LONG REFERENCES user(id)");

            database.execSQL("CREATE INDEX IF NOT EXISTS index_note_created_by_user_id ON note (created_by_user_id)");
            database.execSQL("CREATE INDEX IF NOT EXISTS index_note_updated_by_user_id ON note (updated_by_user_id)");
            database.execSQL("CREATE INDEX IF NOT EXISTS index_user_created_by_user_id ON user (created_by_user_id)");
            database.execSQL("CREATE INDEX IF NOT EXISTS index_user_updated_by_user_id ON user (updated_by_user_id)");
        }
    };

    public static final Migration[] ALL_MIGRATIONS = new Migration[]{MIGRATION_1_2};
}
