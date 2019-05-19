package info.adavis.topsy.turvey.models

import java.util.UUID

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Recipe : RealmObject {
    @PrimaryKey
    var id = UUID.randomUUID().toString()

    var name: String? = null

    var description: String? = null

    var imageResourceId: Int = 0

    var steps: RealmList<RecipeStep>? = null

    var numberOfStars: Int? = null

    constructor() {}

    constructor(name: String, description: String, imageResourceId: Int) {
        this.name = name
        this.description = description
        this.imageResourceId = imageResourceId
    }

    override fun toString(): String {
        return "Recipe{" +
                "id='" + id + '\''.toString() +
                ", name='" + name + '\''.toString() +
                ", description='" + description + '\''.toString() +
                ", imageResourceId=" + imageResourceId +
                '}'.toString()
    }
}
