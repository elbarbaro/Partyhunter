package com.daniel.partyhunter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment that launches other parts of the demo application.
 */
public class Home extends Fragment implements View.OnClickListener{

    MapView mMapView;
    List<MarkerOptions> markersarray = new ArrayList<>();
    ArrayList<Marker> Pines = new ArrayList<>();
    private GoogleMap googleMap;
    private Button buttonname,button;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.home, container,false);

        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        ImageButton rojo = (ImageButton) v.findViewById(R.id.btnrojo);
        rojo.setOnClickListener(this);

        ImageButton azul = (ImageButton) v.findViewById(R.id.btnazul);
        azul.setOnClickListener(this);

        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();
        // latitude and longitude

        double latitude []= {20.676997,20.669579,20.671456,20.678203};
        double longitude []= {-103.387699,-103.368496,-103.368464,-103.370707};
        String textos[]={"Guadalajara","Sitio2","sitio3","Sitio4"};
        int tipo[]={1,2,3,1};

        MarkerOptions marker = new MarkerOptions();






        for (int markers=0; markers<=latitude.length-1;markers++)
        {
            // create marker
            marker.position( new LatLng(latitude[markers], longitude[markers])).title(textos[markers]);

            if (tipo[markers]==1)// Changing marker icon
            {
                marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
            }

            if (tipo[markers] == 2)// Changing marker icon
            {
                marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            }

            if (tipo[markers]==3)// Changing marker icon
            {
                marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            }

            // adding marker
            //googleMap.addMarker(marker);
            markersarray.add(marker);
            Marker Markerobj= googleMap.addMarker(markersarray.get(markers));//se crea los objetos de pines
            Pines.add(Markerobj);//agregarlos al arraylist de Marker

        }

        googleMap.setMyLocationEnabled(true);

        LatLng Guadalajara = new LatLng(20.665625, -103.37548);
        // Perform any camera updates here
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Guadalajara));
        googleMap.animateCamera( CameraUpdateFactory.zoomTo( 13.0f ) );
        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnrojo:
                Pines.get(1).setVisible(false);//Visibilidad de los pines para cambiarlos

                Pines.get(0).setVisible(true);//Visibilidad de los pines para cambiarlos
                Pines.get(3).setVisible(true);//Visibilidad de los pines para cambiarlos



                break;

            case R.id.btnazul:
                Pines.get(1).setVisible(true);//Visibilidad de los pines para cambiarlos


                Pines.get(0).setVisible(false);//Visibilidad de los pines para cambiarlos
                Pines.get(3).setVisible(false);//Visibilidad de los pines para cambiarlos
                break;



        }

    }
}