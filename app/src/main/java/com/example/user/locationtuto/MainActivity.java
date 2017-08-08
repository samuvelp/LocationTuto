package com.example.user.locationtuto;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.StrictMode;
import android.renderscript.Double2;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
private static final String TAG = "Lat and Log";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button BTN_show = (Button) findViewById(R.id.UI_BTN_showLocation);
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);//asking users to turn on the location service
        BTN_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSTracker gpsTracker = new GPSTracker(getApplication());
                Location location = gpsTracker.getLocation();
                //Toast.makeText(getApplicationContext(),"Your check in location: ", Toast.LENGTH_SHORT).show();
                if(location!=null){
                    Double latitude = location.getLatitude();
                    Double longitude = location.getLongitude();

                    try {
                        Toast.makeText(getApplicationContext(),getAddress(latitude,longitude),Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"lat "+ latitude +" long "+ longitude);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
        });


    }
        public String getAddress(Double latitude, Double longitude) throws IOException {
            Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geoCoder.getFromLocation(latitude,longitude,1);
            String addressLine1 = addresses.get(0).getAddressLine(0);
            String addressLine2 = addresses.get(0).getAddressLine(1);
            String addressLine3 = addresses.get(0).getAddressLine(2);

            String address = addressLine1 +" "+addressLine2 +"\n"+addressLine3;
            return address;
        }


}
