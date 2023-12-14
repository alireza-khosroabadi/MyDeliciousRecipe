@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    libs.plugins.recipes.android.apply {
        alias(library)
        alias(hilt)
    }
}

android {
    namespace = "com.delicious.utility"
    buildFeatures {
        buildConfig = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {

    libs.apply {
        testImplementation(junit)
        androidTestImplementation(junit.ext)
    }
}