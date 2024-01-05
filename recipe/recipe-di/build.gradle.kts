plugins {
    libs.plugins.recipes.android.apply {
        alias(library)
        alias(hilt)
    }
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.delicious.recipeDI"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    projects.apply {

        implementation(recipe.recipeDomain)
        implementation(recipe.recipeData)
    }

}
