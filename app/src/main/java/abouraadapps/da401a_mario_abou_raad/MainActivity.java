package abouraadapps.da401a_mario_abou_raad;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("MAIN", "onCreate Main");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.fragment_container) != null) {
            Log.i("MAIN", "onCreate Main");
            if (savedInstanceState != null) {
                Log.i("MAIN", "onCreate Main return");
                return;
            }
            Log.i("MAIN", "onCreate Main WORKS");
            MainFragment mainFragment = new MainFragment();
            fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragment_container, mainFragment, "A");
            ft.commit();
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("onStart", "on start MainActivity");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("onResume", "on Resume MainActivity");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("onPause", "on pause MainActivity");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("onStop", "on stop MainActivity");
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy", "on destroy MainActivity");
    }


    }

