plugins {
    libs.plugins.recipes.android.apply {
        alias(library)
        alias(hilt)
    }
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.delicious.recipeData"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    projects.apply {
        core.apply {
            implementation(network)
            implementation(base)
        }
        implementation(projects.recipe.recipeDomain)
    }

}
