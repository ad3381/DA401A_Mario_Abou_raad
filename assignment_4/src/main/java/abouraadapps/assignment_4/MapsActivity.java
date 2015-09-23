package abouraadapps.assignment_4;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener, GoogleApiClient.ConnectionCallbacks, com.google.android.gms.location.LocationListener {

    static final LatLng orkanen = new LatLng(55.610959, 12.995037);
    static final LatLng home = new LatLng(55.604781, 13.029140);
    static final LatLng niagara = new LatLng(55.609140, 12.992469);
    static final LatLng training = new LatLng(55.611346, 13.006793);
    static final String TAG = "MapsActivity";
    Marker homeMarker, niagaraMarker, trainingMarker, orkanenMarker;
    private GoogleApiClient googleApiClient;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();


        //
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .build();
        googleApiClient.connect();


    }

    @Override
    protected void onResume() {
        super.onResume();

        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {


        mMap.setOnMarkerClickListener(this);
        homeMarker = mMap.addMarker(new MarkerOptions()
                .position(home)
                .title("QuestionA")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        niagaraMarker = mMap.addMarker(new MarkerOptions()
                .position(niagara)
                .title("QuestionB")
                .snippet("Question B")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        trainingMarker = mMap.addMarker(new MarkerOptions()
                .position(training)
                .title("QuestionC")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        orkanenMarker = mMap.addMarker(new MarkerOptions()
                .position(orkanen)
                .title("QuestionD")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

        mMap.setMyLocationEnabled(true);

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        UiSettings mUISetting = mMap.getUiSettings();
        mUISetting.setZoomControlsEnabled(true);
        mUISetting.setMyLocationButtonEnabled(true);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(orkanen));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(16));

        Location myLocation = mMap.getMyLocation();



    }



    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG, "onConnected ");

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setSmallestDisplacement(10);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "onConnectionSuspended");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i(TAG, "onLocationChanged " + location.getLongitude() + location.getLatitude() + "time" + location.getTime());

    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (marker.equals(homeMarker)) {
            Log.i(TAG, "QuestionA");
            Dialog_A mDialogA = new Dialog_A();
            mDialogA.show(ft, "A");
        }
        if (marker.equals(niagaraMarker)) {
            Log.i(TAG, "QuestionB");
            Dialog_B mDialogB = new Dialog_B();
            mDialogB.show(ft, "B");
        }

        if (marker.equals(trainingMarker)) {
            Log.i(TAG, "QuestionC");
            Dialog_C mDialogC = new Dialog_C();
            mDialogC.show(ft, "C");
        }
        if (marker.equals(orkanenMarker)) {
            Log.i(TAG, "QuestionD");
            Dialog_D mDialogD = new Dialog_D();
            mDialogD.show(ft, "D");
        }
        return true;
    }
}



