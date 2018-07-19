package com.example.chenjiayou.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by chenjiayou on 2018/7/18.
 */

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Query ("DELETE FROM WORD_TABLE")
    void deleteAll();

    @Query ("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();
}
