package com.example.roomexample.dataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

//annotate the table
@Entity(tableName="word_table")
public class Word {
    //annotate the primaryKey & NonNull
    //constructor
    @PrimaryKey
    @NonNull
    //column member of the dataBase & its annotate
    @ColumnInfo(name = "word")
    private String mWord;
    //constructor
    public Word(@NonNull String word){this.mWord=word;}
    // get function
    @NonNull
    public String getWord(){return mWord;}
}
