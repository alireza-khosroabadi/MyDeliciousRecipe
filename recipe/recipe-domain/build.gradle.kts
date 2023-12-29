plugins {
    libs.plugins.recipes.android.apply {
        alias(library)
        alias(hilt)
    }
}

android {
    namespace = "com.delicious.recipeDomain"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    projects.apply {
        core.apply {
            implementation(base)
        }
    }

}
