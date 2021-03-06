package it.deeper.popularmovies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import it.deeper.popularmovies.R;
import it.deeper.popularmovies.model.Movie;
import it.deeper.popularmovies.utils.Params;


/**
 * Created by justo on 17/02/18.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder>{

    private Context context;
    private ArrayList<Movie> movies;

    private final MovieClickListener mMovieClickListener;

    public interface MovieClickListener {
        void onClickMovie(int position);
    }

    public MovieAdapter(Context context, ArrayList<Movie> movies, MovieClickListener movieClickListener){
        this.context = context;
        this.movies = movies;
        mMovieClickListener = movieClickListener;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list, parent, false);

        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView imageViewHolder;

        MovieHolder(View itemView) {
            super(itemView);
            imageViewHolder = itemView.findViewById(R.id.image_poster);
            imageViewHolder.setOnClickListener(this);
        }

        public void bind(int position){
            Picasso.with(context)
                    .load(Params.IMAGE_PATH.concat(Params.IMAGE_SIZE[2])
                            .concat(movies.get(position).getPosterPath()))

                    .into(imageViewHolder);
        }

        @Override
        public void onClick(View view) {
            int clickPosition = getAdapterPosition();
            mMovieClickListener.onClickMovie(clickPosition);
        }
    }
}
