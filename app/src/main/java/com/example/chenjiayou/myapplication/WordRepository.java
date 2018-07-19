package com.example.chenjiayou.myapplication;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by chenjiayou on 2018/7/18.
 */

public class WordRepository {

    private WordDao mWordDao;

    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {

        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    LiveData<List<Word>> getAllWords() {

        return mAllWords;
    }

    public void insert(Word word) {

        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAnsyncTaskDao;

        insertAsyncTask(WordDao wordDao) {

            mAnsyncTaskDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {

            mAnsyncTaskDao.insert(words[0]);
            return null;
        }
    }
}
