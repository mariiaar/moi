package com.example.dd.retrofit2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.support.annotation.NonNull;
import android.widget.TextView;


// Data adapting class for adapting the lakes and their info to their individual cards


// VOITAS VAAN FILTTERÖIDÄ PELKÄSTÄÄN JÄRVIEN NIMELLÄ JOS EI KERITÄ MUUTA


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements Filterable {


    private ArrayList<Lake> value;
    // Another list for filtering the lakes
    // https://www.youtube.com/watch?v=sJ-Z9G0SDhc
    private ArrayList<Lake> valueFull;

    public DataAdapter(ArrayList<Lake> value) {
        this.value = value;
        valueFull = new ArrayList<>(value);
    }

    // Creating the next card for the lake
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row,parent,false);
        return new ViewHolder(view);
    }


    // Binds the values from current(value.get(position)) lake to the textViews
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv_lakeName.setText(value.get(position).getNimi());
        holder.tv_lakeCity.setText("Kunta : " + value.get(position).getKuntaNimi());
        float f = Float.parseFloat(value.get(position).getVesiala());

        // Changing ha to km² for lake area
        int a = (int) Math.round(f * 10);
        holder.tv_area.setText("Järven pinta-ala : " + a + " km²");
    }

    // So far useless method to get the size of attributes of the current lake from JSON
    @Override
    public int getItemCount() {
        return value.size();
    }

    // ViewHolder class in the RecyclerView for the lake Card
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_lakeName;
        private TextView tv_lakeCity;
        private TextView tv_area;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_lakeName = (TextView)itemView.findViewById(R.id.tv_lakeName);
            tv_lakeCity = (TextView)itemView.findViewById(R.id.tv_lakeCity);
            tv_area = (TextView)itemView.findViewById(R.id.tv_area);

        }
    }


    // Creates the filtering mechanism for the lake name search
    @Override
    public Filter getFilter() {
        return valueFilter;
    }

    private Filter valueFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Lake> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(valueFull);
            } else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Lake item : valueFull){
                    if (item.getNimi().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            value.clear();
            value.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
