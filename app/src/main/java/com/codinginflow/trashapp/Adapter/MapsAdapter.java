package com.codinginflow.trashapp.Adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codinginflow.trashapp.Activity.ItemMenu;
import com.codinginflow.trashapp.R;

import java.util.ArrayList;

public class MapsAdapter extends RecyclerView.Adapter<MapsAdapter.MapsViewHolder> {
    private ArrayList<ItemMenu> mItemMenu;

    public static class MapsViewHolder extends RecyclerView.ViewHolder{
        public TextView text;

        public MapsViewHolder(View itemView){
            super(itemView);
            text = itemView.findViewById(R.id.textMaps);
        }
    }
    public MapsAdapter(ArrayList<ItemMenu> list){
        mItemMenu = list;
    }
    @NonNull
    @Override
    public MapsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_maps, parent, false);
        MapsViewHolder mvh = new MapsViewHolder(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MapsViewHolder holder, int position) {
        ItemMenu currentItem = mItemMenu.get(position);
        holder.text.setText(currentItem.getNama());
    }

    @Override
    public int getItemCount() {
        return mItemMenu.size();
    }

}
