package com.mobdeve.s16.lagado.kian.mco2_abelgas_lagado_llamado;

import android.widget.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AnimeAdapter extends BaseAdapter {
    private Context context;
    private List<Anime> animeList;

    public AnimeAdapter(Context context, List<Anime> animeList) {
        this.context = context;
        this.animeList = animeList;
    }

    @Override
    public int getCount() {
        return animeList.size();
    }

    @Override
    public Object getItem(int position) {
        return animeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        ImageView thumbnail = convertView.findViewById(R.id.thumbnail);
        TextView title = convertView.findViewById(R.id.title);
        TextView synopsis = convertView.findViewById(R.id.synopsis);
        TextView rating = convertView.findViewById(R.id.rating);

        Anime anime = animeList.get(position);

        thumbnail.setImageResource(anime.getThumbnail());
        title.setText(anime.getTitle());
        synopsis.setText(anime.getSynopsis());
        rating.setText(anime.getRating());

        return convertView;
    }
}
