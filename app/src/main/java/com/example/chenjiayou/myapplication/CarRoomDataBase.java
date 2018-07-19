package com.example.chenjiayou.myapplication;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;

/**
 * Created by chenjiayou on 2018/7/19.
 */

@Database (entities = {Car.class}, version = 1)
public abstract class CarRoomDataBase extends RoomDatabase {

    public abstract CarDao GetCarDao();

    private static CarRoomDataBase INSTANCE;

    static CarRoomDataBase getDatabase(Context context) {

        synchronized (CarRoomDataBase.class) {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, CarRoomDataBase.class, "car_database")
                               .addCallback(new Callback() {

                                   @Override
                                   public void onOpen(
                                           @NonNull SupportSQLiteDatabase db) {

                                       super.onOpen(db);
                                       Single.timer(500, TimeUnit.MILLISECONDS)
                                             .subscribe(new Consumer<Long>() {

                                                 @Override
                                                 public void accept(Long aLong) throws Exception {

                                                     INSTANCE.GetCarDao()
                                                             .insert(new Car("吉利", "沃尔沃"));

                                                 }
                                             });

                                   }
                               })
                               .build();
            }
        }
        return INSTANCE;
    }

}
