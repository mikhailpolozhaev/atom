package com.example.fractalmedia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fractalmedia.ItemDetailActivity;
import com.example.fractalmedia.ItemDetailFragment;
import com.example.fractalmedia.R;
import com.example.fractalmedia.model.DetailsMovies;
import com.example.fractalmedia.retrofit.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomMoviesRecyclerViewAdapter extends RecyclerView.Adapter<CustomMoviesRecyclerViewAdapter.ViewHolder> {

    private final List<DetailsMovies> mValues;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DetailsMovies item = (DetailsMovies) view.getTag();
                Context context = view.getContext();
                Intent intent = new Intent(context, ItemDetailActivity.class);
                intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.getId().toString());
                intent.putExtra(ItemDetailFragment.ARG_ITEM_TITLE, item.getTitle());
                context.startActivity(intent);
        }
    };

    public CustomMoviesRecyclerViewAdapter() {
        mValues = new ArrayList<>();
    }


    @Override
    public void onBindViewHolder(
            final CustomMoviesRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mIdView.setText(mValues.get(position).getTitle());
        holder.mContentView.setText(mValues.get(position).getOverview());
        Picasso.get()
                .load(ApiClient.URL_IMG + mValues.get(position).getBackdropPath())
                .into(holder.imageView);
        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public CustomMoviesRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void addItems(List<DetailsMovies> postItems) {
        mValues.addAll(postItems);
        notifyDataSetChanged();
    }

    public void clear() {
        mValues.clear();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageId)
        ImageView imageView;
        @BindView(R.id.id_text)
        TextView mIdView;
        @BindView(R.id.content)
        TextView mContentView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }
}
