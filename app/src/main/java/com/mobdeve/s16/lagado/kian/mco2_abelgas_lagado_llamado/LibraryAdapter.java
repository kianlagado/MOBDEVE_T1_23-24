package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder> {

    Context context;
    List<TestAnime> sampleEntries;
    ActivityResultLauncher<Intent> launcher;

    public static String TITLE_TAG = "TITLE";
    public static String IMAGE_TAG = "IMAGE";
    public static String DESC_TAG = "DESC";
    public static String RATING_TAG = "RATING";
    public static String YEAR_TAG = "YEAR";
    public static String USER_RATING_TAG = "USER_RATING";
    public static String USER_PROGRESS_TAG = "USER_PROGRESS";
    public static String USER_STATUS_TAG = "STATUS";
    public static String TYPE_TAG = "TYPE";
    public static String POS_TAG = "POS";

    public LibraryAdapter(LibraryActivity libraryActivity, List<TestAnime> sampleEntries, ActivityResultLauncher<Intent> launcher) {
        this.context = libraryActivity;
        this.sampleEntries = sampleEntries;
        this.launcher = launcher;
    }

    public void updateEntries(List<TestAnime> updatedEntries) {
        this.sampleEntries = updatedEntries;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public LibraryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.library_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TestAnime entryItem = sampleEntries.get(position);
        holder.entryTitle.setText(entryItem.getTitle());
        holder.entryDate.setText(entryItem.getDate());
        holder.entryRate.setText(entryItem.getUserRating());
        holder.entryImage.setImageResource(entryItem.getThumbnail());

        holder.entryProgress.setText(entryItem.getUserProgress());
        // TODO: Should obtain total eps/chapters from API call
        if (entryItem.getType().equals("Anime")) holder.entryTotal.setText("/Total eps");
        else if (entryItem.getType().equals("Manga")) holder.entryTotal.setText("/Total chs");

        // Goes to detail activity
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

        // Goes to edit entry activity
        holder.entryEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(context, EditEntryActivity.class);
                editIntent.putExtra(TITLE_TAG, entryItem.getTitle());
                editIntent.putExtra(IMAGE_TAG, entryItem.getThumbnail());
                editIntent.putExtra(USER_STATUS_TAG, entryItem.getUserStatus());
                editIntent.putExtra(USER_PROGRESS_TAG, entryItem.getUserProgress());
                editIntent.putExtra(USER_RATING_TAG, entryItem.getUserRating());
                editIntent.putExtra(TYPE_TAG, entryItem.getType());
                editIntent.putExtra(POS_TAG, holder.getAdapterPosition());
                //context.startActivity(editIntent);
                launcher.launch(editIntent);
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
        TextView entryTotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            entryImage = itemView.findViewById(R.id.item_image);
            entryTitle = itemView.findViewById(R.id.item_title);
            entryDate = itemView.findViewById(R.id.item_date);
            entryProgress = itemView.findViewById(R.id.item_progress);
            entryTotal = itemView.findViewById(R.id.progress_total);
            entryRate = itemView.findViewById(R.id.item_rating);
            entryEdit = itemView.findViewById(R.id.item_edit);
        }
    }
}
