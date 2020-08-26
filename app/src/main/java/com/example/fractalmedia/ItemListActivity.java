package com.example.fractalmedia;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.fractalmedia.adapter.CustomMoviesRecyclerViewAdapter;
import com.example.fractalmedia.model.MoviesResponse;
import com.example.fractalmedia.retrofit.ApiClient;
import com.example.fractalmedia.services.TMDBService;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener {

    private MoviesResponse moviesResponse;

    private CustomMoviesRecyclerViewAdapter adapter;

    private int currentPage = 1;
    private boolean isLastPage = false;
    private boolean isLoading = false;


    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View recyclerView = findViewById(R.id.item_list);
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        swipeRefresh.setOnRefreshListener(this);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CustomMoviesRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        loadData();

        recyclerView.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                swipeRefresh.setRefreshing(true);
                isLoading = true;
                currentPage++;
                loadData();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

    }

    private void loadData() {

        TMDBService service = ApiClient.getClient().create(TMDBService.class);
        Call<MoviesResponse> call = service.listPopularMovie(ApiClient.API_KEY, currentPage);

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                moviesResponse = response.body();
                Log.e("ItemListFragment", "response: " + response.body().toString());
                adapter.addItems(moviesResponse.getResults());
                swipeRefresh.setRefreshing(false);
                if (currentPage >= moviesResponse.getTotalPages()) {

                    isLastPage = true;
                }
                isLoading = false;
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e("ItemDetailFragment", "throwable: " + t.getMessage());
            }
        });
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        isLastPage = false;
        adapter.clear();
        loadData();
    }

}
