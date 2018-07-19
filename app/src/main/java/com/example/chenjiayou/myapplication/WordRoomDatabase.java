package com.example.chenjiayou.myapplication;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by chenjiayou on 2018/7/18.
 */

@Database (entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                INSTANCE = Room.databaseBuilder(context, WordRoomDatabase.class,
                                                "word_database").addCallback(sCallback).build();
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(
                @NonNull SupportSQLiteDatabase db) {

            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;

        PopulateDbAsync(WordRoomDatabase db) {

            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            mDao.deleteAll();
            Word word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            return null;
        }
    }

}
