package com.example.roomexample.dataBase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

// create the room dataBase
@Database(entities={Word.class},version=1)
public abstract class WordRoomDataBase  extends RoomDatabase {
    public abstract WordDao wordDao();
    // let the dataBase only one time create for each version based on Context

    private static volatile WordRoomDataBase INSTANCE;
    static WordRoomDataBase getDataBase(final Context context)
    {
        if(INSTANCE==null)
        { synchronized(WordRoomDataBase.class)
            {if(INSTANCE==null)
                {INSTANCE= Room.databaseBuilder(context.getApplicationContext()
                                                    ,WordRoomDataBase.class
                                                    ,"word_dataBase")
                                                    //.addCallback(sRoomDatabaseCallback)
                                                    .build()
                                                    ;
                }
            }
        }return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback
            =new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;

        PopulateDbAsync(WordRoomDataBase db) {
            mDao = db.wordDao();
        }
        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Word word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            return null;
        }
    }
}
