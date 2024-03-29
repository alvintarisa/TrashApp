package com.codinginflow.trashapp.Fragment;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.codinginflow.trashapp.Adapter.KategoriAdapter;
import com.codinginflow.trashapp.Activity.MapsActivity;
import com.codinginflow.trashapp.R;
import com.codinginflow.trashapp.model.Kategori;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    private RecyclerView recyclerView;
    private Button btnLihat;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rv_home_kategori);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));

        ArrayList<Kategori> kategoris = new ArrayList<>();
        kategoris.add(new Kategori("Plastik",R.drawable.plastic));
        kategoris.add(new Kategori("Botol",R.drawable.botol));
        kategoris.add(new Kategori("Kertas",R.drawable.papers));
        kategoris.add(new Kategori("Kardus",R.drawable.kardus));

        KategoriAdapter kategoriAdapter = new KategoriAdapter(getActivity(),kategoris);
        recyclerView.setAdapter(kategoriAdapter);

        btnLihat = view.findViewById(R.id.btn_home_lihat);
        btnLihat.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_home_lihat :
                Intent intent = new Intent(getContext(), MapsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
