package abouraadapps.assignment_5;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marioabouraad on 15-09-22.
 */
public class QuoteFragment extends Fragment {

    List<String> quotes = new ArrayList<String>();

    QuoteAdapter quoteAdapter;
    ProgressBar progressbar;
    ListView listview;


    public QuoteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
    }


    //  @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_quote, container, false);
        listview = (ListView) v.findViewById(R.id.quote_list);

        progressbar = (ProgressBar) v.findViewById(R.id.progress_bar);
        progressbar.setIndeterminate(true);

        quoteAdapter = new QuoteAdapter(getContext(), R.layout.fragment_quote, quotes);

        //quoteAdapter = new QuoteAdapter(quotes, getActivity().getLayoutInflater());
        listview.setAdapter(quoteAdapter);
        listview.setDivider(null);

        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        listview.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {


            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater menuInflater = mode.getMenuInflater();
                menuInflater.inflate(R.menu.menu_delete, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                mode.setTitle("Selected");
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_delete:
                        SparseBooleanArray selected = quoteAdapter.getSelectedItems();
                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                String selectedItem = quoteAdapter.getItem(selected.keyAt(i));
                                quoteAdapter.remove(selectedItem);
                            }
                        }
                        quoteAdapter.notifyDataSetChanged();
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }


            @Override
            public void onDestroyActionMode(ActionMode mode) {
                quoteAdapter.removeSelection();
            }


            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                mode.setTitle(listview.getCheckedItemCount() + " Items are Checked");
                quoteAdapter.viewSelection(position);

            }
        });

        return v;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        //   menuInflater.inflate(R.menu.menu_add, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                progressbar.setVisibility(View.VISIBLE);
                try {
                    new DownloadQuote().execute(new URL("https://api.github.com/zen?access_token=b179193f364cd0be84894b9da1841dcb8424b191"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return true;

            case R.id.menu_delete:
                return true;

        }


      /*  int id = item.getItemId();

        if (id == R.id.menu_delete) {
            return true;
        }

        if (id == R.id.menu_add) {
            mProgressBar.setVisibility(ProgressBar.VISIBLE);
            URL url;

            try {
                url = new URL("https://api.github.com/zen?access_token=0f892e365071c7e778a020e463d715b8ccb816f5");
                new DownloadQuote().execute(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return true;
            }



        }*/

        return super.onOptionsItemSelected(item);
    }


    private class DownloadQuote extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //quoteAdapter.notifyDataSetChanged();
            progressbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            URL url = params[0];

            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream input = new BufferedInputStream(urlConnection.getInputStream());
                    return quote(input);

                } finally {
                    urlConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String quote) {
            super.onPostExecute(quote);
            quotes.add(quote);
            progressbar.setVisibility(View.GONE);

            quoteAdapter.notifyDataSetChanged();
            listview.smoothScrollToPosition(quotes.size() - 1);
        }


        private String quote(InputStream input) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
            StringBuilder sb = new StringBuilder();

            String line;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }
    }


}
