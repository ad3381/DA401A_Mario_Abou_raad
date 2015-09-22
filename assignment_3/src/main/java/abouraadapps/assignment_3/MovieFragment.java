package abouraadapps.assignment_3;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;

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
 * Created by marioabouraad on 15-09-17.
 */
public class MovieFragment extends Fragment {
    ArrayList<Movie> mMoviesList = new ArrayList<>();
    JSONAdapter jsonAdapter;
    ProgressBar progressBar;
    Movie mMovie;

    public MovieFragment() {
        //Required Empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.movie_fragment, container, false);

        progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);

        GridView gridview = (GridView) v.findViewById(R.id.gridview);

        jsonAdapter = new JSONAdapter(mMoviesList, getActivity().getLayoutInflater());

        gridview.setAdapter(jsonAdapter);


        try {
            URL url;
            url = new URL("https://api-v2launch.trakt.tv/movies/popular?extended=images");
            DownloadMovies dm = new DownloadMovies();
            dm.execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return v;
    }

    private class DownloadMovies extends AsyncTask<URL, Void, List<Movie>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Movie> doInBackground(URL... params) {
            URL url = params[0];

            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                String apiKey = "492a165927bfaff86b3030454939981d4e2d94c50515e15e42f41fbf57481a44";
                String apiVersion = "2";

                // urlConnection.setRequestProperty("Content-type", "application/json");
                urlConnection.setRequestProperty("trakt-api-version", apiVersion);
                urlConnection.setRequestProperty("trakt-api-key", apiKey);

                try {
                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    return createListOfMovies(inputStream);
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        private List<Movie> createListOfMovies(InputStream inputStream) throws IOException, JSONException {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder sb = new StringBuilder();

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            mMovie = new Movie();
            String s = sb.toString();
            return mMovie.fromJson(new JSONArray(s));
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);
            mMoviesList.addAll(movies);
            jsonAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);

        }

    }


}
