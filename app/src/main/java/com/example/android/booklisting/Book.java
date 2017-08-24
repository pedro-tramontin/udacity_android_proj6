package com.example.android.booklisting;

import java.util.List;

/**
 * Created by pedro on 8/19/17.
 */

public class Book {
    private String mTitle;
    private List<String> mAuthor;

    public Book(String mTitle, List<String> mAuthor) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

    public List<String> getAuthor() {
        return mAuthor;
    }
}
