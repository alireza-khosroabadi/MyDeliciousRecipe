plugins {
    libs.plugins.recipes.android.apply {
        alias(library)
        alias(library.compose)
    }
}

android {
    namespace = "com.delicious.systemdesign"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(projects.core.ui)
    api(libs.androidx.compose.material3)
}