package abouraadapps.da401a_mario_abou_raad;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by marioabouraad on 15-09-07.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    MainActivity activity;
    Button button;

        public MainFragment() {

        }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("onCreateView", "created");
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i("FragA", "onCREATE IMPORTANT ");
        super.onActivityCreated(savedInstanceState);
        activity = (MainActivity) getActivity();
        button = (Button) getActivity().findViewById(R.id.button);
        button.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        Log.i("FragA", "onClick IMPORTANT ");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        QuoteFragment qf = new QuoteFragment();
        ft.replace(R.id.fragment_container, qf);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("B");
        ft.commit();

    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i("onStart", "on start fragA");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("onResume", "on resume fragA");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("onPause", "on pause fragA");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("onStop", "on stop fragA");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("onDestroviewy", "on destroyview fragA");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy", "on destroy FragA");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("onDestach", "on destach fragA");
    }


}


