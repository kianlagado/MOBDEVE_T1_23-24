package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder> {

    Context context;
    List<Anime> sampleEntries;

    public static String TITLE_TAG = "TITLE";
    public static String IMAGE_TAG = "IMAGE";
    public static String DESC_TAG = "DESC";
    public static String RATING_TAG = "RATING";
    public static String YEAR_TAG = "YEAR";

    public LibraryAdapter(LibraryActivity libraryActivity, List<Anime> sampleEntries) {
        this.context = libraryActivity;
        this.sampleEntries = sampleEntries;
    }

    public void updateEntries(List<Anime> updatedEntries) {
        this.sampleEntries = updatedEntries;
        notifyDataSetChanged();
    }



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
        holder.entryDate.setText(entryItem.getDate());
        holder.entryRate.setText(entryItem.getUserRating());
        holder.entryImage.setImageResource(entryItem.getThumbnail());

        if (entryItem.getType() == "Anime") holder.entryProgress.setText(entryItem.getUserProgress() + " eps");
        else if (entryItem.getType() == "Manga") holder.entryProgress.setText(entryItem.getUserProgress() + " chs");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailActivity.class);
                // TODO: I think these should be converted to API calls in next phase
                i.putExtra(TITLE_TAG, entryItem.getTitle());
                i.putExtra(IMAGE_TAG, entryItem.getThumbnail());
                i.putExtra(DESC_TAG, entryItem.getSynopsis());
                i.putExtra(RATING_TAG, entryItem.getRating());
                i.putExtra(YEAR_TAG, entryItem.getDate());
                context.startActivity(i);
            }
        });

        holder.entryEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: edit entry layout
                Intent i = new Intent(context, EditEntryActivity.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sampleEntries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView entryImage;
        ImageButton entryEdit;
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
            entryEdit = itemView.findViewById(R.id.item_edit);
        }
    }
}
