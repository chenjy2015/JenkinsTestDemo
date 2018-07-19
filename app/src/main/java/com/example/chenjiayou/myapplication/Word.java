package com.example.chenjiayou.myapplication;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by chenjiayou on 2018/7/18.
 */

@Entity (tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo (name = "word")
    private String mWord;

    public Word(@NonNull String word) {

        this.mWord = word;
    }

    public String getWord() {

        return this.mWord;
    }
}
