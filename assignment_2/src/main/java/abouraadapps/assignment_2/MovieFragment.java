package abouraadapps.assignment_2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by marioabouraad on 15-09-10.
 */
public class MovieFragment extends Fragment {


    public MovieFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MovieListFragment ml = new MovieListFragment();

        View v = inflater.inflate(R.layout.fragment_movie, container, false);

        TextView yearView = (TextView) v.findViewById(R.id.year_textView);
        TextView summary = (TextView) v.findViewById(R.id.summary_textView);
        TextView titleView = (TextView) v.findViewById(R.id.title_imageView);
        ImageView imageView = (ImageView) v.findViewById(R.id.fanart_imageView);

        Bundle args = getArguments();


        summary.setText(args.getString("Summary"));
        imageView.setImageResource(args.getInt("Fanart"));
        titleView.setText(args.getString("Title"));
        yearView.setText(args.getString("Year"));



        return v;
    }


}
