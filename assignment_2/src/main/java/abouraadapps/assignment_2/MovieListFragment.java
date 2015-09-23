package abouraadapps.assignment_2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marioabouraad on 15-09-13.
 */
public class MovieListFragment extends Fragment implements FragmentManager.OnBackStackChangedListener {

    List<Movie> movieList;

    public MovieListFragment() {
        movieList = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TypedArray movies = getResources().obtainTypedArray(R.array.movies);

        for (int i = 0; i < movies.length(); i++) {
            TypedArray movieArray = getResources().obtainTypedArray(movies.getResourceId(i, 0));

            Movie movie = new Movie(movieArray.getString(0), movieArray.getString(1), movieArray.getString(2), movieArray.getResourceId(3, 0), movieArray.getResourceId(4, 0));

            movieList.add(movie);
            movieArray.recycle();
        }
        movies.recycle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_movie, container, false);

        GridView gridView = (GridView) v.findViewById(R.id.movie_grid);

        MovieAdapter movieAdapter = new MovieAdapter(movieList, getActivity().getLayoutInflater());

        gridView.setAdapter(movieAdapter);

        gridView.setOnItemClickListener(new GridClick());

        return v;
    }

    @Override
    public void onBackStackChanged() {

    }

    private class GridClick implements AdapterView.OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Bundle args = new Bundle();
            args.putString("Title", movieList.get(position).getTitle());
            args.putString("Year", movieList.get(position).getYear());
            args.putInt("Fanart", movieList.get(position).getFanart());
            args.putString("Summary", movieList.get(position).getSummary());

            MovieFragment movieFragment = new MovieFragment();
            movieFragment.setArguments(args);

            // Create a FragmentTransaction
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace current fragment with quoteDayFragment
            transaction.replace(R.id.fragment_container, movieFragment);

            // Add current fragment to the back stack
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }
}
