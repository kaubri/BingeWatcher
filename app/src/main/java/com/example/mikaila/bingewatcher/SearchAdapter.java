package com.example.mikaila.bingewatcher;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mikaila on 5/31/2017.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder>{

    private Context context; // may not need
    private List<Anime> animeList;
    private LayoutInflater layoutInflater;

    public SearchAdapter(Context context, List<Anime> animeList) {
        layoutInflater = LayoutInflater.from(context);
        this.animeList = animeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = layoutInflater.inflate(R.layout.search_result, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Anime current = animeList.get(position);
        holder.setData(current, position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "this is anime: " + current.get_title_english(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, ResultActivity.class);
                intent.putExtra("anime", current);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title;
        private TextView episodes;
        private TextView duration;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.show_img);
            title = (TextView) itemView.findViewById(R.id.show_title);
            episodes = (TextView) itemView.findViewById(R.id.show_episodes);
            duration = (TextView) itemView.findViewById(R.id.show_duration);
        }

        public void setData(Anime current, int position) {
            this.title.setText(current.get_title_english());
            this.episodes.setText("Episodes: " + current.get_total_episodes());
            this.duration.setText("Duration: " + current.get_duration() + " mins");
        }
    }
}
