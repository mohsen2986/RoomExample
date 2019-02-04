package com.example.roomexample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.roomexample.dataBase.Word;
import com.example.roomexample.ui.WordListAdapter;
import com.example.roomexample.dataBase.ViewModel;

import java.util.List;

import static com.example.roomexample.NewWord.EXTRA_REPLY;


public class MainActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE=1;
    public ViewModel mWordViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        final WordListAdapter adapter=new WordListAdapter(MainActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mWordViewModel= ViewModelProviders.of(this).get(ViewModel.class);
        mWordViewModel.getAllWords().observe(this,new Observer<List<Word>> ()
                {

                    @Override
                    public void onChanged(@Nullable List<Word> words) {
                    adapter.setWords(words );
                    }
                }
        );

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewWord.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK){
            Word word=new Word(data.getStringExtra(NewWord.EXTRA_REPLY));
            mWordViewModel.inset(word);
        }else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_savaed,
                    Toast.LENGTH_LONG).show();
        }
    }
}
