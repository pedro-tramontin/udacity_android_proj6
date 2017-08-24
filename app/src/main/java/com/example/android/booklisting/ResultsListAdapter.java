package com.example.android.booklisting;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pedro on 8/23/17.
 */

public class ResultsListAdapter extends ArrayAdapter<Book> {
    public ResultsListAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_result_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        Book currentItem = getItem(position);

        holder.bookTitle.setText(currentItem.getTitle());
        holder.bookAuthors.setText(currentItem.getAuthor().toString());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.book_title)
        TextView bookTitle;

        @BindView(R.id.book_authors)
        TextView bookAuthors;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
