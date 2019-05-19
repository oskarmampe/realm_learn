package info.adavis.topsy.turvey.features.recipes;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import info.adavis.topsy.turvey.R;
import info.adavis.topsy.turvey.models.Recipe;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class RecipesAdapter extends RealmRecyclerViewAdapter<Recipe, RecipesAdapter.ViewHolder>
{

    public RecipesAdapter(@Nullable OrderedRealmCollection<Recipe> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView recipeImage;
        TextView recipeName;
        TextView recipeDescription;

        public ViewHolder (View v)
        {
            super( v );

            recipeImage = (ImageView) v.findViewById(R.id.recipe_image);
            recipeName = (TextView) v.findViewById(R.id.recipe_name);
            recipeDescription = (TextView) v.findViewById(R.id.recipe_description);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position)
    {
        Recipe recipe = getData().get( position );

        holder.recipeName.setText(recipe.getName());
        holder.recipeDescription.setText(recipe.getDescription());

        Picasso.with(holder.recipeImage.getContext())
                .load(recipe.getImageResourceId())
                .resize(340, 200)
                .centerCrop()
                .into(holder.recipeImage);
    }
}