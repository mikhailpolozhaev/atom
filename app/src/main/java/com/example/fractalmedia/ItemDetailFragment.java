package com.example.fractalmedia;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.fractalmedia.model.Companies;
import com.example.fractalmedia.model.Genres;
import com.example.fractalmedia.model.Languages;
import com.example.fractalmedia.model.Movie;
import com.example.fractalmedia.retrofit.ApiClient;
import com.example.fractalmedia.retrofit.TMDBService;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_ITEM_TITLE = "item_title";
    private static final String SEPARATOR = ", ";

    private Movie mItem;

    @BindView(R.id.imageView)
    ImageView imagen;
    @BindView(R.id.item_title)
    TextView titleTextView;
    @BindView(R.id.item_genres)
    TextView genresTextView;
    @BindView(R.id.item_detail)
    TextView detailTextView;
    @BindView(R.id.item_popularity_content)
    TextView popularityTextView;
    @BindView(R.id.item_budget_content)
    TextView budgetTextView;
    @BindView(R.id.item_status_content)
    TextView statusTextView;
    @BindView(R.id.item_language_content)
    TextView languageTextView;
    @BindView(R.id.item_companies_content)
    TextView companiesTextView;
    Unbinder unbinder;

    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            String idMovie = getArguments().getString(ARG_ITEM_ID);
            TMDBService service = ApiClient.getClient().create(TMDBService.class);
            Call<Movie> call = service.getMovieDetail(idMovie, ApiClient.API_KEY);

            call.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    Log.e("ItemDetailFragment", "response: " + response.body());

                    mItem = response.body();

                    Picasso.get()
                            .load("https://image.tmdb.org/t/p/original" + mItem.getBackdropPath())
                            .into(imagen);
                    titleTextView.setText(mItem.getTitle());
                    detailTextView.setText(mItem.getOverview());
                    popularityTextView.setText(String.valueOf(mItem.getPopularity()));
                    genresTextView.setText(Genres.getGenresWithDelimiter(mItem.getGenres(),
                            SEPARATOR));
                    budgetTextView.setText(String.valueOf(mItem.getBudget()));
                    statusTextView.setText(mItem.getStatus());
                    languageTextView.setText(
                            Languages.getLanguagesWithDelimiter(mItem.getSpokenLanguages(), SEPARATOR)
                    );
                    companiesTextView.setText(
                            Companies.getCompaniesWithDelimiter(mItem.getProductionCompanies(),
                                    SEPARATOR)
                    );
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    Log.e("ItemDetailFragment", "throwable: " + t.getMessage());

                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}