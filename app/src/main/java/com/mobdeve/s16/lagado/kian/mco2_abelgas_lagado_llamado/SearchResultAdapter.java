package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class SearchResultAdapter<T> extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private ArrayList<T> searchResults;
    private Context context;
    private String type;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) { this.listener = listener;}

    public SearchResultAdapter(Context context, ArrayList<T> searchResults, String type) {
        this.context = context;
        this.searchResults = searchResults;
        this.type = type;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView search_thumbnail;
        TextView search_title;
        TextView search_genre;
        TextView search_date;
        TextView search_status;
        TextView search_rating;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            search_thumbnail = itemView.findViewById(R.id.search_item_image);
            search_title = itemView.findViewById(R.id.search_item_title);
            search_genre = itemView.findViewById(R.id.search_item_genres);
            search_date = itemView.findViewById(R.id.search_item_date);
            search_status = itemView.findViewById(R.id.search_item_status);
            search_rating = itemView.findViewById(R.id.search_item_rating);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }

                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_result_item, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        T currentItem = searchResults.get(position);

        if (type.equals("Anime")) {
            TestAnime anime = (TestAnime) currentItem;
            holder.search_title.setText(anime.getTitle());
            holder.search_genre.setText(anime.getGenres());
            holder.search_date.setText(anime.getDate());
            holder.search_status.setText(anime.getStatus());
            holder.search_rating.setText(anime.getScore());

            Picasso.get()
                    .load(anime.getImageUrl())
                    .into(holder.search_thumbnail);
        }
        else if (type.equals("Manga")) {
            Manga manga = (Manga) currentItem;
            holder.search_title.setText(manga.getTitle());
            holder.search_genre.setText(manga.getGenres());
            holder.search_date.setText(manga.getDate());
            holder.search_status.setText(manga.getStatus());
            holder.search_rating.setText(manga.getScore());

            Picasso.get()
                    .load(manga.getImageUrl())
                    .into(holder.search_thumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }


}
