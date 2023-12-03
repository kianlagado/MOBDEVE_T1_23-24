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

import java.util.List;

public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.MangaViewHolder> {

    private List<Manga> mangaList;
    private Context context;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Constructor
    public MangaAdapter(Context context, List<Manga> mangaList) {
        this.context = context;
        this.mangaList = mangaList;
    }

    public void updateData(List<Manga> updatedData) {
        this.mangaList = updatedData;
        notifyDataSetChanged();
    }

    // ViewHolder class
    public static class MangaViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView title;
        TextView synopsis;
        TextView rating;

        public MangaViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
            synopsis = itemView.findViewById(R.id.genres);
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
    public MangaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MangaViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MangaViewHolder holder, int position) {
        Manga manga = mangaList.get(position);

        // Use Picasso to load the image from the URL
        Picasso.get().load(manga.getImageUrl()).into(holder.thumbnail);

        holder.title.setText(manga.getTitle());
        holder.synopsis.setText(manga.getSynopsis());
        holder.rating.setText(String.valueOf(manga.getScore())); // Assuming score is a double
    }

    @Override
    public int getItemCount() {
        return mangaList.size();
    }
}
