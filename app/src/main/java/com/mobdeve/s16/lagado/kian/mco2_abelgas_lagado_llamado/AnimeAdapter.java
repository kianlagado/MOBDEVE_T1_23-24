package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso; // Ensure Picasso library is included in your project

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {

    private List<TestAnime> animeList;
    private Context context;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Constructor
    public AnimeAdapter(Context context, List<TestAnime> animeList) {
        this.context = context;
        this.animeList = animeList;
    }

    public void updateData(List<TestAnime> updatedData) {
        this.animeList = updatedData;
        notifyDataSetChanged();
    }

    // ViewHolder class
    public static class AnimeViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView title;
        TextView synopsis;
        TextView rating;

        public AnimeViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
            synopsis = itemView.findViewById(R.id.synopsis);
            rating = itemView.findViewById(R.id.rating);

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
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new AnimeViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
        TestAnime anime = animeList.get(position);

        // Use Picasso to load the image from the URL
        Picasso.get().load(anime.getImageUrl()).into(holder.thumbnail);

        holder.title.setText(anime.getTitle());
        holder.synopsis.setText(anime.getSynopsis());
        holder.rating.setText(String.valueOf(anime.getScore())); // Assuming score is a double
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }
}
