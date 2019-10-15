package com.dicoding.aplikasiphotomurid.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dicoding.aplikasiphotomurid.Dataset.DataModel;
import com.dicoding.aplikasiphotomurid.R;

import java.util.ArrayList;

public class MuridAdapter extends RecyclerView.Adapter<MuridAdapter.ListViewHolder> {
    private OnItemClickCallback onItemClickCallback;
    private ArrayList<DataModel> listMurid;
    public MuridAdapter(ArrayList<DataModel> listMurid){
        this.listMurid = listMurid;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_data_siswa, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, final int position) {
        DataModel murid = listMurid.get(position);
        listViewHolder.tvName.setText(murid.getNama());
        listViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listMurid.get(listViewHolder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMurid.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(DataModel dataModel);
    }
}
