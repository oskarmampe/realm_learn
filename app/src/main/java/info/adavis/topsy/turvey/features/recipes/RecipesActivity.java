package info.adavis.topsy.turvey.features.recipes;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.List;

import info.adavis.topsy.turvey.R;
import info.adavis.topsy.turvey.db.RecipesDataProvider;
import info.adavis.topsy.turvey.db.TopsyTurveyDataSource;
import info.adavis.topsy.turvey.models.Recipe;
import io.realm.OrderedRealmCollection;

public class RecipesActivity extends AppCompatActivity
{
    private static final String TAG = RecipesActivity.class.getSimpleName();

    private RecyclerView recipesRecyclerView;
    private TopsyTurveyDataSource dataSource;
    private RecipesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recipesRecyclerView = (RecyclerView) findViewById(R.id.recipes_recycler_view);

        dataSource = new TopsyTurveyDataSource();
        dataSource.open();

        setupRecyclerView();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        //---------------- REALMADAPTER TEST -------------------------
//        dataSource.createRecipe(RecipesDataProvider.recipesList.get(0));
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                dataSource.createRecipe(RecipesDataProvider.recipesList.get(1));
//            }
//        }, 3000);
        for (Recipe recipe : RecipesDataProvider.recipesList) {
            dataSource.createRecipe(recipe);
        }

        List<Recipe> allRecipes = dataSource.getAllRecipes();
        for (Recipe recipe : allRecipes) {
            Log.i(TAG, "recipe: " + recipe);
        }

        //------------ UPDATE RECIPE -----------------
//        dataSource.modifyDescription();

        //This will completely update the record, as an unmanaged realm object changes ALL fields to those specified.
        //Fields not specified will be null
//        Recipe unManaged = new Recipe("Red velvet", "yummy", R.drawable.cake_2);
//        This recipe usually has RecipeSteps attached.
        //However, seen as no steps were provided, the steps are empty.
//        unManaged.setId(allRecipes.get(2).getId());

//        dataSource.createRecipe(unManaged);

        //---------- DELETE RECIPE -----------------
        //dataSource.deleteRecipe(allRecipes.get(0));
//        dataSource.deleteAllRecipes();
    }

    @Override
    protected void onDestroy()
    {
        dataSource.close();

        super.onDestroy();
    }

    private void setupRecyclerView()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recipesRecyclerView.setLayoutManager(layoutManager);

        recipesRecyclerView.setHasFixedSize(true);

        adapter = new RecipesAdapter((OrderedRealmCollection<Recipe>) dataSource.getAllRecipes(), true);
        recipesRecyclerView.setAdapter(adapter);
    }

}
