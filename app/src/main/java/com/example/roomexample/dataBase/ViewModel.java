package com.example.roomexample.dataBase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

//view model help survive data between configuration changes
// & acts between UI & repository
public class ViewModel extends AndroidViewModel {
    private WordRepository mWordRepository;
    private LiveData<List<Word>> mAllWord;
    public ViewModel(@NonNull Application application) {
        super(application);
        mWordRepository=new WordRepository(application);
        mAllWord= mWordRepository.getAllWords();
    }
    public LiveData<List<Word>> getAllWords(){return mAllWord;}
    public void inset(Word word){mWordRepository.insert(word);}
}
