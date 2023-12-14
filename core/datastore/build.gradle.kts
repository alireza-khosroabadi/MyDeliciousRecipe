plugins {
    libs.plugins.recipes.android.apply {
        alias(library)
        alias(hilt)
    }
}

android {
    namespace = "com.delicious.datastore"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.androidx.dataStore.core)
    implementation(libs.kotlinx.coroutines.android)
}