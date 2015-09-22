package abouraadapps.assignment_2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by marioabouraad on 15-09-08.
 */
public class MovieAdapter extends BaseAdapter {

    List<Movie> movieList;
    LayoutInflater layoutInflater;

    public MovieAdapter(List<Movie> movieList, LayoutInflater layoutInflater) {
        this.movieList = movieList;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.view_movie, parent, false);
        }


        ImageView image = (ImageView) convertView.findViewById(R.id.poster_movie_view);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.movie_title);
        TextView yearTextView = (TextView) convertView.findViewById(R.id.movie_year);

        image.setImageDrawable(((Movie) getItem(position)).getPoster());
        titleTextView.setText(((Movie) getItem(position)).getTitle());
        yearTextView.setText(((Movie) getItem(position)).getYear());

        return convertView;
    }
}
