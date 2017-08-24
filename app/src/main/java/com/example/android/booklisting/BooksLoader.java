package com.example.android.booklisting;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.Collections;
import java.util.List;

public class BooksLoader extends AsyncTaskLoader<List<Book>> {

    private static final String TAG = "BooksLoader";

    private String mUrl;

    public BooksLoader(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(TAG, "onStartLoading");

        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        Log.i(TAG, "loadInBackground");

        if (mUrl == null)
            return Collections.emptyList();

        return QueryUtils.fetchBooksData(mUrl);
    }
}
