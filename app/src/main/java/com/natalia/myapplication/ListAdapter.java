package com.natalia.myapplication;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private ArrayList<Forecast> listaDadosTempo;

    public ListAdapter(ArrayList<Forecast> lista) {
        this.listaDadosTempo = lista;
    }

    public void updateData(ArrayList<Forecast> newData) {
        listaDadosTempo.clear();
        for (int i = 0; i < newData.size() && i < 7; i++) {
            listaDadosTempo.add(newData.get(i));
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dataDiaSemana;
        TextView min;
        TextView max;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dataDiaSemana = itemView.findViewById(R.id.dateText);
            min = itemView.findViewById(R.id.minText);
            max = itemView.findViewById(R.id.maxText);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_tempo, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Forecast itemPosition = listaDadosTempo.get(position);
        viewHolder.dataDiaSemana.setText(itemPosition.getWeekday());
        viewHolder.max.setText(String.valueOf(itemPosition.getMax())+ "°");
        viewHolder.min.setText(String.valueOf(itemPosition.getMin())+ "°");
    }

    @Override
    public int getItemCount() {
        return Math.min(listaDadosTempo.size(), 7);
    }
}
