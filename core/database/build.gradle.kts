@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    libs.plugins.recipes.android.apply {
        alias(library)
        alias(room)
        alias(hilt)
    }
}

android {
    namespace = "com.delicious.database"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}