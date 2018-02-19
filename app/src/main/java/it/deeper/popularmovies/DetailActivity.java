package it.deeper.popularmovies;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import it.deeper.popularmovies.model.Movie;
import it.deeper.popularmovies.utils.Params;

public class DetailActivity extends AppCompatActivity {

    private static Movie mMovie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        settingsToolbar();
        fillData();
    }

    private void fillData() {

    }

    private void settingsToolbar() {
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");

        final ImageView backdrop = findViewById(R.id.backdrop);
        loadBackdrop(backdrop);

        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(mMovie.getOriginalTitle());
                    backdrop.setAlpha(.7f);
                    isShow = true;
                } else if (isShow) {
                    // There should a space between double quote otherwise it won't work
                    collapsingToolbar.setTitle(" ");
                    backdrop.setAlpha(1f);
                    isShow = false;
                }
            }
        });
    }

    private void loadBackdrop(ImageView backdrop) {
        Picasso.with(this)
                .load(Params.IMAGE_PATH.concat(mMovie.getBackdropPath()))
                .into(backdrop);
    }

    public static void setMovieDetails(Movie movie){
        mMovie = movie;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
