plugins {
    libs.plugins.recipes.android.apply {
        alias(library)
        alias(hilt)
    }
}

android {
    namespace = "com.delicious.analytics"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.androidx.core.ktx)
    implementation(libs.firebase.analytics)
    implementation(libs.kotlinx.coroutines.android)
}