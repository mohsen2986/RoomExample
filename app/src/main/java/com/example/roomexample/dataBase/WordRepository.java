package com.example.roomexample.dataBase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
// create repository for clean API & safe threading UI
public class WordRepository {
    //member of the DAO
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWord;
    // constructor create dataBate based on app
    WordRepository(Application application)
    {
        WordRoomDataBase dataBase=WordRoomDataBase.getDataBase(application);
        mWordDao=dataBase.wordDao();
        mAllWord=mWordDao.getAllWords();
    }
    LiveData<List<Word>> getAllWords(){return mAllWord;}

    // insert the word in safe thread by AsyncTask
    void insert(Word word)
    {new insertAsyncTask(mWordDao).execute(word);}

    private static class insertAsyncTask extends AsyncTask<Word,Void,Void>
    {   private WordDao mAsyncTaskDao;
        insertAsyncTask(WordDao dao) {mAsyncTaskDao=dao;}

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }
}
