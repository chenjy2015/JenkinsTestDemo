package com.example.chenjiayou.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chenjiayou on 2018/7/18.
 */

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private       List<Word>     mWords; // Cached copy of words

    WordListAdapter(Context context) {

        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(
            @NonNull WordViewHolder holder, int position) {

        if (mWords != null) {
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        } else {
            holder.wordItemView.setText("no word");
        }
    }

    @Override
    public int getItemCount() {

        return mWords == null ? 0 : mWords.size();
    }

    public void setWords(List<Word> words) {

        mWords = words;
        notifyDataSetChanged();
    }

    class WordViewHolder extends RecyclerView.ViewHolder {

        private final TextView wordItemView;

        private WordViewHolder(View itemView) {

            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }

}
