package abouraadapps.assignment_3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by marioabouraad on 15-09-17.
 */
public class JSONAdapter extends BaseAdapter {

    List<Movie> mMoviesList;
    LayoutInflater mInflater;
    Bitmap mBitmap;
    ImageView mCover;
    ProgressBar progressBar;

    public JSONAdapter(List<Movie> mMoviesList, LayoutInflater mInflater) {
        this.mMoviesList = mMoviesList;
        this.mInflater = mInflater;
    }

    @Override
    public int getCount() {
        return mMoviesList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMoviesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //  ViewHolder holder;
        // Movie movie = mMoviesList.get(position);
        //  progressBar = (ProgressBar) convertView.findViewById(R.id.progress_bar);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.movie_item, null);
        }
        Movie movie = (Movie) getItem(position);
        try {

            URL url;
            url = new URL(movie.getCoverUrl());
            DownloadMoviePoster dmp = new DownloadMoviePoster();
            mBitmap = dmp.execute(url).get();
            // progressBar = (ProgressBar) convertView.findViewById(R.id.progress_bar);

        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        mCover = (ImageView) convertView.findViewById(R.id.movieImage);
        mCover.setImageBitmap(mBitmap);

        TextView titleView = (TextView) convertView.findViewById(R.id.text_title);
        titleView.setText(movie.getTitle());

        TextView yearView = (TextView) convertView.findViewById(R.id.text_year);
        yearView.setText(movie.getYear());

        return convertView;
    }


    private class DownloadMoviePoster extends AsyncTask<URL, Void, Bitmap> {

        protected Bitmap doInBackground(URL... params) {
            URL url = params[0];

            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream in = new BufferedInputStream((urlConnection).getInputStream());
                    return BitmapFactory.decodeStream(in);

                } finally {
                    urlConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
      /*  ImageView image;

        public DownloadMoviePoster() {
            this.image = image;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(URL... params) {
            URL url = params[0];
            Bitmap poster;

         /*  try {
                url = new URL(url);
            }catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("trakt-api-version", "2");
                urlConnection.setRequestProperty("trakt-api-key", "492a165927bfaff86b3030454939981d4e2d94c50515e15e42f41fbf57481a44");
                try {
                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    poster = BitmapFactory.decodeStream(inputStream);
                    return poster;
                } finally {
                    urlConnection.disconnect();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap poster) {
            image.setImageBitmap(poster);
        }
    } */

}
