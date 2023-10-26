package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SearchResultAdapter extends ArrayAdapter<Search> {

    public SearchResultAdapter(@NonNull Context context, @NonNull List<Search> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_result_item, parent, false);
        }

        Search currentItem = getItem(position);

        ImageView imageView = convertView.findViewById(R.id.search_item_image);
        TextView titleTextView = convertView.findViewById(R.id.search_item_title);
        TextView ratingTextView = convertView.findViewById(R.id.search_item_rating);

        if (currentItem != null) {
            imageView.setImageResource(currentItem.getThumbnail()); // Using getThumbnail() method from Anime class
            titleTextView.setText(currentItem.getTitle());
            ratingTextView.setText(currentItem.getRating()); // Directly setting the rating
        }

        return convertView;
    }
}
