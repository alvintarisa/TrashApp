package com.codinginflow.trashapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.codinginflow.trashapp.Adapter.MapsAdapter;
import com.codinginflow.trashapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private GoogleMap mMap;
    private FusedLocationProviderClient client;
    private Marker mFilkom, mCurrLoc, mRektorat;
    private DatabaseReference mDatabase, mLokasi;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ArrayList<ItemMenu> itemMenus = new ArrayList<>();
        itemMenus.add(new ItemMenu("Filkom"));
        itemMenus.add(new ItemMenu("Rektorat"));
        mRecyclerView = findViewById(R.id.recycleMap);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MapsAdapter(itemMenus);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mLokasi = mDatabase.child("Lokasi");
        mLokasi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String lat = dataSnapshot.child("Filkom").child("Latitude").getValue().toString();
                String lng = dataSnapshot.child("Filkom").child("Longitude").getValue().toString();
                double lat1 = Double.parseDouble(lat);
                double lng1 = Double.parseDouble(lng);
                LatLng filkom = new LatLng(lat1, lng1);
                mFilkom = mMap.addMarker(new MarkerOptions().position(filkom).title("Marker di FILKOM"));
                mFilkom.setTag(0);
                String latRek = dataSnapshot.child("Rektorat").child("Latitude").getValue().toString();
                String lngRek = dataSnapshot.child("Rektorat").child("Longitude").getValue().toString();
                double latRek1 = Double.parseDouble(latRek);
                double lngRek1 = Double.parseDouble(lngRek);
                LatLng rektorat = new LatLng(latRek1, lngRek1);
                mFilkom = mMap.addMarker(new MarkerOptions().position(rektorat).title("Marker di FILKOM"));
                mFilkom.setTag(0);
                float zoomLevel = 16.f; //This goes up to 21
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(filkom, zoomLevel));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
