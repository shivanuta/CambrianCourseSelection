package com.example.cambriancourseselection.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private static DatabaseClient mInstance;
    private Context mContext;

    private AppDatabase appDatabase;

    private DatabaseClient(Context context) {
        this.mContext = context;
        appDatabase = Room.databaseBuilder(mContext, AppDatabase.class, "cambrian").allowMainThreadQueries().build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(context);
        }
        return mInstance;
    }

    public AppDatabase getAppDb() {
        return appDatabase;
    }
}