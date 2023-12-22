plugins {
    libs.plugins.recipes.android.apply {
        alias(library)
        alias(hilt)
    }
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.delicious.homeData"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    projects.apply {
        core.apply {
            implementation(network)
            implementation(base)
            implementation(ui)
        }
        implementation(projects.home.homeDomain)
    }

}