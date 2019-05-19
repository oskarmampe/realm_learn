package info.adavis.topsy.turvey.db

import java.util.ArrayList
import java.util.Arrays

import info.adavis.topsy.turvey.R
import info.adavis.topsy.turvey.models.Recipe
import info.adavis.topsy.turvey.models.RecipeStep
import io.realm.RealmList

object RecipesDataProvider {
    var recipesList: MutableList<Recipe>

    init {
        recipesList = ArrayList()

        addRecipe(Recipe("Cake", "Wonderful cake", R.drawable.cake_1))

        addRecipe(Recipe("Pie", "Delicious Pie", R.drawable.pie_1))

        addRecipe(Recipe("Pound Cake", "Fluffy cake", R.drawable.cake_2),
                RecipeStep(1, "mix all the ingredients"),
                RecipeStep(2, "preheat the oven"),
                RecipeStep(3, "stir"),
                RecipeStep(4, "bake"))
    }

    private fun addRecipe(recipe: Recipe, vararg steps: RecipeStep) {
        if (steps.size > 0) {
            recipe.steps = RealmList()
            recipe.steps.addAll(Arrays.asList(*steps))
        }


        recipesList.add(recipe)
    }
}