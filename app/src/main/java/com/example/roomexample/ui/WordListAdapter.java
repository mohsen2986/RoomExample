package com.example.roomexample.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.roomexample.R;
import com.example.roomexample.dataBase.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder{
        private TextView wordTextView;
        private WordViewHolder (View itemView)
        {super(itemView);
            wordTextView=itemView.findViewById(R.id.textView);
        }
    }
    private LayoutInflater mInflater;
    private List<Word> mWords;
    public WordListAdapter(Context context){this.mInflater=LayoutInflater.from(context);}


    public void setWords(List<Word> words)
    {
        mWords=words;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word current=mWords.get(position);
        holder.wordTextView.setText(current.getWord());
    }

    @Override
    public int getItemCount() {
        if (mWords==null) return 0;
        return mWords.size();
    }


}
