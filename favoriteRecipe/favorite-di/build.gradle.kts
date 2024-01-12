plugins {
    libs.plugins.recipes.android.apply {
        alias(library)
        alias(hilt)
    }
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.delicious.favoriteDI"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    projects.apply {
        implementation(core.datastore)
        implementation(favoriteRecipe.favoriteDomain)
        implementation(favoriteRecipe.favoriteData)
    }
}
