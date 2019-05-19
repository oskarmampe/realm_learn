package info.adavis.topsy.turvey.features.recipes

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log

import info.adavis.topsy.turvey.R
import info.adavis.topsy.turvey.db.RecipesDataProvider
import info.adavis.topsy.turvey.db.TopsyTurveyDataSource
import info.adavis.topsy.turvey.models.Recipe
import io.realm.OrderedRealmCollection

class RecipesActivity : AppCompatActivity() {

    private var recipesRecyclerView: RecyclerView? = null
    private var dataSource: TopsyTurveyDataSource? = null
    private var adapter: RecipesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        recipesRecyclerView = findViewById(R.id.recipes_recycler_view) as RecyclerView

        dataSource = TopsyTurveyDataSource()
        dataSource!!.open()

        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()

        //---------------- REALM ADAPTER TEST -------------------------
        //        dataSource.createRecipe(RecipesDataProvider.recipesList.get(0));
        //
        //        new Handler().postDelayed(new Runnable() {
        //            @Override
        //            public void run() {
        //                dataSource.createRecipe(RecipesDataProvider.recipesList.get(1));
        //            }
        //        }, 3000);
        for (recipe in RecipesDataProvider.recipesList) {
            dataSource!!.createRecipe(recipe)
        }

        val allRecipes = dataSource!!.allRecipes
        for (recipe in allRecipes) {
            Log.i(TAG, "recipe: $recipe")
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

    override fun onDestroy() {
        dataSource!!.close()

        super.onDestroy()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recipesRecyclerView!!.layoutManager = layoutManager

        recipesRecyclerView!!.setHasFixedSize(true)

        adapter = RecipesAdapter(dataSource!!.allRecipes as OrderedRealmCollection<Recipe>, true)
        recipesRecyclerView!!.adapter = adapter
    }

    companion object {
        private val TAG = RecipesActivity::class.java!!.getSimpleName()
    }

}
