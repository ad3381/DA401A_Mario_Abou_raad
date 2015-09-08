package abouraadapps.da401a_mario_abou_raad;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by marioabouraad on 15-09-07.
 */
public class QuoteFragment extends Fragment {

    MainActivity activity;
    TextView text;
    ArrayList<String> quoteList;
    int select;

    public QuoteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("onCreateView", "created fragB");
        View v = inflater.inflate(R.layout.fragment_quote, container, false);
        activity = (MainActivity) getActivity();
        text = (TextView) v.findViewById(R.id.text_quote);

        quoteList = new ArrayList<>();
        quoteList.add("Life is not a problem to be solved, but a reality to be experienced");
        quoteList.add("You must be the change you wish to see in the world.");
        quoteList.add("Keep your face to the sunshine and you cannot see a shadow");
        quoteList.add("The only thing that interferes with my learning is my education");

        Log.i("FragA", "onClick still working ");

        Random randomNumber = new Random();
        select = randomNumber.nextInt(4);
        String quote = quoteList.get(select);
        text.setText(quote);


        return v;

    }




    @Override
    public void onStart() {
        Log.i("Frag B", "onStart ");
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Frag B", "onRes B ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Frag B", "onPaus B ");
    }

    @Override
    public void onStop() {
        Log.i("Frag B", "onStop ");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.i("Frag B", "onDestroyView B ");
        super.onDestroyView();
    }



    @Override
    public void onDetach() {
        Log.i("Frag B", "onDetach B ");
        super.onDetach();
    }


}
