plugins {
    libs.plugins.recipes.android.apply {
        alias(feature)
        alias(library.compose)
        alias(hilt)
    }
}

android {
    namespace = "com.delicious.feature.favoriteRecipe.favoriteRecipeUI"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    projects.apply {
        implementation(favoriteRecipe.favoriteDomain)
        implementation(favoriteRecipe.favoriteDi)
        implementation(core.base)
        core.apply {
            implementation(utility)
            implementation(systemdesign)
            implementation(ui)
        }
    }
}
