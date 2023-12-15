plugins {
    libs.plugins.recipes.android.apply {
        alias(feature)
        alias(library.compose)
        alias(hilt)
    }
}

android {
    namespace = "com.delicious.feature.home.homeUI"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    projects.apply {
        implementation(home.homeDomain)
        implementation(core.base)
        core.apply {
            implementation(utility)
            implementation(systemdesign)
            implementation(ui)
        }
    }

}