package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private List<Search> searchResults;
    private Context context;

    public SearchResultAdapter(Context context, List<Search> searchResults) {
        this.context = context;
        this.searchResults = searchResults;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_result_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Search currentItem = searchResults.get(position);
        holder.search_thumbnail.setImageResource(currentItem.getThumbnail());
        holder.search_title.setText(currentItem.getTitle());
        holder.search_rating.setText(currentItem.getRating());
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView search_thumbnail;
        TextView search_title;
        TextView search_rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            search_thumbnail = itemView.findViewById(R.id.search_item_image);
            search_title = itemView.findViewById(R.id.search_item_title);
            search_rating = itemView.findViewById(R.id.search_item_rating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detailIntent = new Intent(context, DetailActivity.class);
                    context.startActivity(detailIntent);
                }
            });
        }
    }


}
