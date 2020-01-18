package com.allegra.allegra.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.allegra.allegra.DetailActivity;
import com.allegra.allegra.R;
import com.allegra.allegra.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
    private Context context;
    private List<Result> result;
    private AdapterView.OnItemClickListener listener;
    public static final String KEY_ARTIST = "idArtist";
    public static final String KEY_ALBUM = "idAlbum";
    public static final String KEY_TRACK = "idTrack";
    public static final String KEY_NAMA_ARTIST = "artist";
    public static final String KEY_NAMA_TRACK = "track";
    public static final String KEY_COVER = "cover";
    String apiKey = "d5f0d9OX0Bn95JbZwSD0Ja5x1mMlJb4B6qAcZFCkhQT8hoKXDQlGxefY";

    public ResultAdapter(Context context, List<Result> result, AdapterView.OnItemClickListener listener) {
        this.context = context;
        this.result = result;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Result res = this.result.get(position);
        holder.tvTrack.setText(res.getTrack());
        holder.tvArtist.setText(res.getArtist());
        Picasso.with(context)
            .load(res.getCover())
            .into(holder.ivCover);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(KEY_ARTIST, res.getIdArtist());
                intent.putExtra(KEY_ALBUM, res.getIdAlbum());
                intent.putExtra(KEY_TRACK, res.getIdTrack());
                intent.putExtra(KEY_NAMA_ARTIST, res.getArtist());
                intent.putExtra(KEY_NAMA_TRACK, res.getTrack());
                intent.putExtra(KEY_COVER, res.getCover());

                context.startActivity(intent);

                //Toast.makeText(v.getContext(), "track : " + res.getTrack(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder {
        public TextView tvTrack;
        public TextView tvArtist;
        public ImageView ivCover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTrack = itemView.findViewById(R.id.track);
            tvArtist = itemView.findViewById(R.id.artist);
            ivCover = (ImageView) itemView.findViewById(R.id.cover);
        }
    }



}
