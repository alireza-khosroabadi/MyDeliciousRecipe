plugins {
    libs.plugins.recipes.android.apply {
        alias(feature)
        alias(library.compose)
        alias(hilt)
    }
}

android {
    namespace = "com.delicious.feature.recipe.recipeUI"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    projects.apply {
        implementation(recipe.recipeDomain)
        implementation(recipe.recipeDi)
        implementation(core.base)
        core.apply {
            implementation(utility)
            implementation(systemdesign)
            implementation(ui)
        }
    }

}
