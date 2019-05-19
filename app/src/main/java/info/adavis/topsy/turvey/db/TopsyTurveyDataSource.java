package info.adavis.topsy.turvey.db;

import android.util.Log;

import java.util.List;

import info.adavis.topsy.turvey.models.Recipe;
import info.adavis.topsy.turvey.models.RecipeFields;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class TopsyTurveyDataSource
{
    private static final String TAG = TopsyTurveyDataSource.class.getSimpleName();
    private Realm realm;

    public void open()
    {
        realm = Realm.getDefaultInstance();
        Log.d( TAG, "open: database opened" );
    }

    public void close()
    {
        realm.close();
        Log.d( TAG, "close: database closed" );
    }

    public void createRecipe (final Recipe recipe)
    {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(recipe);
            }
        });

        Log.d(TAG, "createRecipe: the id: " + recipe.getId());
    }

    public List<Recipe> getAllRecipes(){
        return realm.where(Recipe.class)
                .findAll();
    }

    public void modifyDescription(){
        final Recipe recipe = realm.where(Recipe.class).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                recipe.setDescription("Wonderful yellow cake!");
            }
        });
    }

    public void deleteRecipe(Recipe recipe){
        final Recipe recipeManaged = realm.where(Recipe.class).equalTo(RecipeFields.ID, recipe.getId()).findFirst();

        //Delete doesn't support cascading so you have to delete any child entities first.
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                recipeManaged.deleteFromRealm();
            }
        });
    }

    public void deleteAllRecipes(){
        final RealmResults<Recipe> recipes = realm.where(Recipe.class).findAll();

        //Delete doesn't support cascading so you have to delete any child entities first.
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                recipes.deleteAllFromRealm();
            }
        });
    }

}