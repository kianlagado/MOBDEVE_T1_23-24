package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {

    private List<TestAnime> animeList;
    private Context context;

    private OnItemClickListener Listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        Listener = listener;
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }

    }

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new AnimeViewHolder(view, Listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
        TestAnime anime = animeList.get(position);
        holder.thumbnail.setImageResource(anime.getThumbnail());
        holder.title.setText(anime.getTitle());
        holder.synopsis.setText(anime.getSynopsis());
        holder.rating.setText(anime.getRating());
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }
}
