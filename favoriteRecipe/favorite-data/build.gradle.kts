plugins {
    libs.plugins.recipes.android.apply {
        alias(library)
        alias(hilt)
    }
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.delicious.favoriteData"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    projects.apply {
        core.apply {
            api(network)
            implementation(base)
            implementation(ui)
            implementation(datastore)
        }
        implementation(projects.favoriteRecipe.favoriteDomain)
    }
}
