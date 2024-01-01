plugins {
    libs.plugins.recipes.android.apply {
        alias(library)
        alias(hilt)
    }
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.delicious.homeDI"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    projects.apply {

        implementation(home.homeDomain)
        implementation(home.homeData)
    }

}
