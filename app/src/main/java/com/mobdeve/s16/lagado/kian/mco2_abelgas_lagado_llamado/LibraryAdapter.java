package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LibraryAdapter<T> extends RecyclerView.Adapter<LibraryAdapter<T>.ViewHolder> {
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

    private Context context;
    private ArrayList<T> dataList;
    private String type;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {this.listener = listener;}

    public LibraryAdapter(Context context, ArrayList<T> dataList, String type) {
        this.context = context;
        this.dataList = dataList;
        this.type = type;
    }

    public void updateEntries(ArrayList<T> updatedData) {
        this.dataList = updatedData;
        notifyDataSetChanged();
    }

    // ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView entryImage;
        ImageButton entryEdit;
        TextView entryTitle;
        TextView entryDate;
        TextView userStatus;
        TextView userRating;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            entryImage = itemView.findViewById(R.id.item_image);
            entryTitle = itemView.findViewById(R.id.item_title);
            entryDate = itemView.findViewById(R.id.item_date);
            userStatus = itemView.findViewById(R.id.user_status);
            userRating = itemView.findViewById(R.id.user_rating);
            entryEdit = itemView.findViewById(R.id.item_edit);

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
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.library_item, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        T data = dataList.get(position);

        if (type.equals("Anime")) {
            TestAnime anime = (TestAnime) data;
            Picasso.get().load(anime.getImageUrl()).into(holder.entryImage); // Use Picasso to load the image from the URL
            holder.entryTitle.setText(anime.getTitle());
            holder.entryDate.setText(anime.getDate());
            holder.userStatus.setText(anime.getUserStatus());
            holder.userRating.setText(anime.getUserRating());
        }
        else if (type.equals("Manga")) {
            Manga manga = (Manga) data;
            Picasso.get().load(manga.getImageUrl()).into(holder.entryImage); // Use Picasso to load the image from the URL
            holder.entryTitle.setText(manga.getTitle());
            holder.entryDate.setText(manga.getDate());
            holder.userStatus.setText(manga.getUserStatus());
            holder.userRating.setText(manga.getUserRating());
        }

        // Goes to edit entry activity
//        holder.entryEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent editIntent = new Intent(context, EditEntryActivity.class);
////                editIntent.putExtra(TITLE_TAG, entryItem.getTitle());
////                editIntent.putExtra(IMAGE_TAG, entryItem.getThumbnail());
////                editIntent.putExtra(USER_STATUS_TAG, entryItem.getUserStatus());
////                editIntent.putExtra(USER_PROGRESS_TAG, entryItem.getUserProgress());
////                editIntent.putExtra(USER_RATING_TAG, entryItem.getUserRating());
////                editIntent.putExtra(TYPE_TAG, entryItem.getType());
//                context.startActivity(editIntent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
