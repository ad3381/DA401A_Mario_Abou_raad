package abouraadapps.assignment_3_g;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

/**
 * Created by marioabouraad on 15-09-22.
 */
public class QuoteFragment extends Fragment implements View.OnClickListener {
    ArrayList<String> quotes = new ArrayList<>();
    QuoteAdapter quoteAdapter;
    ProgressBar progressbar;

    public QuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_quote, container, false);

        progressbar = (ProgressBar) v.findViewById(R.id.progress_bar);

        ListView listview = (ListView) v.findViewById(R.id.quote_list);
        quoteAdapter = new QuoteAdapter(quotes, getActivity().getLayoutInflater());
        listview.setAdapter(quoteAdapter);

        ImageView fab = (ImageView) v.findViewById(R.id.float_button);
        fab.setOnClickListener(this);

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onClick(View view) {
        URL url;
        try {
            url = new URL("https://api.github.com/zen?access_token=0f892e365071c7e778a020e463d715b8ccb816f5");
            new DownloadQuote().execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private class DownloadQuote extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            Log.i("doInBackground", "InonDoInBackground");
            URL url = params[0];

            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    //Get inputStream
                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    //Read inputStream
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String quote;
                    quote = bufferedReader.readLine();
                    Log.i("doInBackground", quote);
                    return quote;

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
            progressbar.setVisibility(View.GONE);
            quotes.add(quote);
            quoteAdapter.notifyDataSetChanged();
        }

    }
}