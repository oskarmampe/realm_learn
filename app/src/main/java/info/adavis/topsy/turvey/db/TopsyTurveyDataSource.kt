package info.adavis.topsy.turvey.db

import android.util.Log

import info.adavis.topsy.turvey.models.Recipe
import info.adavis.topsy.turvey.models.RecipeFields
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults

class TopsyTurveyDataSource {
    private var realm: Realm? = null

    val allRecipes: List<Recipe>
        get() = realm!!.where<Recipe>(Recipe::class.java)
                .findAll()

    fun open() {
        realm = Realm.getDefaultInstance()
        Log.d(TAG, "open: database opened")
    }

    fun close() {
        realm!!.close()
        Log.d(TAG, "close: database closed")
    }

    fun createRecipe(recipe: Recipe) {
        realm!!.executeTransaction { realm -> realm.insertOrUpdate(recipe) }

        Log.d(TAG, "createRecipe: the id: " + recipe.id)
    }

    fun modifyDescription() {
        val recipe = realm!!.where<Recipe>(Recipe::class.java).findFirst()

        realm!!.executeTransaction { recipe.description = "Wonderful yellow cake!" }
    }

    fun deleteRecipe(recipe: Recipe) {
        val recipeManaged = realm!!.where<Recipe>(Recipe::class.java).equalTo(RecipeFields.ID, recipe.id).findFirst()

        //Delete doesn't support cascading so you have to delete any child entities first.
        realm!!.executeTransaction { recipeManaged.deleteFromRealm() }
    }

    fun deleteAllRecipes() {
        val recipes = realm!!.where<Recipe>(Recipe::class.java).findAll()

        //Delete doesn't support cascading so you have to delete any child entities first.
        realm!!.executeTransaction { recipes.deleteAllFromRealm() }
    }

    companion object {
        private val TAG = TopsyTurveyDataSource::class.java!!.getSimpleName()
    }

}