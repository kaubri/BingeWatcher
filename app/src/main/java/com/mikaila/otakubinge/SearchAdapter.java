package com.mikaila.otakubinge;

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
 * <h1>SearchAdapter</h1>
 * SearchAdapter populates the RecyclerView with anime search results
 *
 * @author  Mikaila Smith
 * @version 1.0
 * @since   2017-05-16
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder>{

    private Context context; // may not need
    private List<Anime> animeList;
    private LayoutInflater layoutInflater;

    /**
     * Constructor for SearchAdapter - sets list of animes and layout inflator
     * @param context
     * @param animeList
     */
    public SearchAdapter(Context context, List<Anime> animeList) {
        layoutInflater = LayoutInflater.from(context);
        this.animeList = animeList;
    }

    /**
     * Creates view holder
     * @param parent Parent viewGroup
     * @param viewType viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = layoutInflater.inflate(R.layout.search_result, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    /**
     * Binds view holder
     * @param holder Holder
     * @param position Position in list
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Anime current = animeList.get(position);
        holder.setData(current, position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ResultActivity.class);
                intent.putExtra("anime", current);
                context.startActivity(intent);
            }
        });
    }

    /**
     * Gets item count of animeList
     * @return Number of search results for anime query
     */
    @Override
    public int getItemCount() {
        return animeList.size();
    }

    /**
     * MyViewHolder class
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title;
        private TextView episodes;
        private TextView duration;

        /**
         * Constructor for MyViewHolder
         * @param itemView Item view
         */
        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.show_img);
            title = (TextView) itemView.findViewById(R.id.show_title);
            episodes = (TextView) itemView.findViewById(R.id.show_episodes);
            duration = (TextView) itemView.findViewById(R.id.show_duration);
        }

        /**
         * Sets anime data in search result on UI
         * @param current Current anime
         * @param position Position
         */
        public void setData(Anime current, int position) {
            new LoadImageFromUrl(img).execute(current.get_image_url());

            title.setText(current.get_title_english());
            episodes.setText("Episodes: " + current.get_total_episodes());
            duration.setText("Duration: " + current.get_duration() + " mins");
        }
    }
}
