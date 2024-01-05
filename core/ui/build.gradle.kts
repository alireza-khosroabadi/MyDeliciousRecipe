
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    libs.plugins.recipes.android.apply {
        alias(library)
        alias(library.compose)
        alias(hilt)
    }
}

android {
    namespace = "com.delicious.ui"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

}

dependencies {
    libs.androidx.apply {
        api(animation.android)
        api(compose.ui.tooling.preview)
        api(tracing.ktx)
        implementation(window.manager)
        implementation(navigation.compose)
        api(compose.ui.util)
        api(compose.foundation)
        api(compose.constraintlayout)
        debugApi(compose.ui.tooling)
    }

    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)

}
