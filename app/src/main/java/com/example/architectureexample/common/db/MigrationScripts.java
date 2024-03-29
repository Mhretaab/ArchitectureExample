package com.example.architectureexample.common.db;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public final class MigrationScripts {

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            database.execSQL("CREATE TABLE `user` (`first_name` TEXT, `last_name` TEXT, `email` TEXT, `password` TEXT, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `uuid` TEXT NOT NULL, `date_created` INTEGER NOT NULL, `date_updated` INTEGER NOT NULL, `created_by_user_id` INTEGER, `updated_by_user_id` INTEGER, `street` TEXT, `state` TEXT, `city` TEXT, `post_code` INTEGER, FOREIGN KEY(`created_by_user_id`) REFERENCES `user`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`updated_by_user_id`) REFERENCES `user`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION )");

            database.execSQL("CREATE TABLE `note` (`title` TEXT, `description` TEXT, `priority` INTEGER NOT NULL, `user_id` INTEGER, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `uuid` TEXT NOT NULL, `date_created` INTEGER NOT NULL, `date_updated` INTEGER NOT NULL, `created_by_user_id` INTEGER, `updated_by_user_id` INTEGER, FOREIGN KEY(`user_id`) REFERENCES `user`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`created_by_user_id`) REFERENCES `user`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`updated_by_user_id`) REFERENCES `user`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
            database.execSQL("INSERT INTO `note`(`title`, `description`, `priority`, `id`, `uuid`, `date_created`, `date_updated`)\n" +
                    "SELECT `title`, `description`, `priority`, `id`, `uuid`, 1569163524, 1569163524 \n" +
                    "FROM `note_table`");
            database.execSQL("DROP TABLE `note_table`");

            database.execSQL("CREATE INDEX `index_note_title` ON `note` (`title`)");
            database.execSQL("CREATE INDEX `index_note_created_by_user_id` ON `note` (`created_by_user_id`)");
            database.execSQL("CREATE INDEX `index_note_updated_by_user_id` ON `note` (`updated_by_user_id`)");
            database.execSQL("CREATE INDEX `index_note_user_id` ON `note` (`user_id`)");
            database.execSQL("CREATE UNIQUE INDEX `index_note_uuid` ON `note` (`uuid`)");
            database.execSQL("CREATE INDEX `index_user_created_by_user_id` ON `user` (`created_by_user_id`)");
            database.execSQL("CREATE UNIQUE INDEX `index_user_email` ON `user` (`email`)");
            database.execSQL("CREATE INDEX `index_user_updated_by_user_id` ON `user` (`updated_by_user_id`)");
            database.execSQL("CREATE UNIQUE INDEX `index_user_uuid` ON `user` (`uuid`)");

            database.execSQL("CREATE VIEW `note_detail` AS SELECT note.title, note.description, note.priority, user.first_name || user.last_name AS owner FROM note LEFT OUTER JOIN user ON user.id=note.user_id");
        }
    };

    public static final Migration[] ALL_MIGRATIONS = new Migration[]{MIGRATION_1_2};
}
