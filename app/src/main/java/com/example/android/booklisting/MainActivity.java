package com.example.android.booklisting;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager
        .LoaderCallbacks<List<Book>> {

    private static final String TAG = "MainActivity";

    private static final String GOOGLE_BOOKS_API_URL = "https://www.googleapis" +
            ".com/books/v1/volumes?q=%s";

    ResultsListAdapter resultsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        disableConnectionReuseIfNecessary();

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        resultsListAdapter = new ResultsListAdapter(this, 0);

        showFragment(new EmptySearchFragment());
        getSupportLoaderManager().initLoader(1, null, this);
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.search_content, fragment).commit();
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        Log.i(TAG, "onCreateLoader");

        return new BooksLoader(this, String.format(GOOGLE_BOOKS_API_URL, "android"));
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        Log.i(TAG, "onLoadFinished");

        resultsListAdapter.clear();
        resultsListAdapter.addAll(books);

        ResultsFragment resultsFragment = new ResultsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("resultsFragment", resultsFragment);

        resultsFragment.setArguments(bundle);

        showFragment(resultsFragment);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        Log.i(TAG, "onLoaderReset");
    }

    private void disableConnectionReuseIfNecessary() {
        // Work around pre-Froyo bugs in HTTP connection reuse.
        if (Integer.parseInt(Build.VERSION.SDK) < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }
}
