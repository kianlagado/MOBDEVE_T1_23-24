package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder> {

    Context context;
    List<Anime> sampleEntries;

    public LibraryAdapter(LibraryActivity libraryActivity, List<Anime> sampleEntries) {
        this.context = libraryActivity;
        this.sampleEntries = sampleEntries;
    }

    /*
    TODO: use with result launcher if needed
    public void updateEntries(List<Anime> updatedEntries) {
        this.sampleEntries = updatedEntries;
        notifyDataSetChanged();
    }
    */


    @NonNull
    @Override
    public LibraryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.library_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Anime entryItem = sampleEntries.get(position);
        holder.entryTitle.setText(entryItem.getTitle());
        holder.entryDate.setText(entryItem.getYear());
        holder.entryProgress.setText(entryItem.getUserProgress());
        holder.entryRate.setText(entryItem.getUserRating());
        holder.entryImage.setImageResource(entryItem.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return sampleEntries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView entryImage;
        TextView entryTitle;
        TextView entryDate;
        TextView entryProgress;
        TextView entryRate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            entryImage = itemView.findViewById(R.id.item_image);
            entryTitle = itemView.findViewById(R.id.item_title);
            entryDate = itemView.findViewById(R.id.item_date);
            entryProgress = itemView.findViewById(R.id.item_progress);
            entryRate = itemView.findViewById(R.id.item_rating);
        }
    }
}
