import java.util.Properties

plugins {
    libs.plugins.recipes.android.apply {
        alias(library)
        alias(hilt)
    }
    alias(libs.plugins.secrets)
}

android {
    namespace = "com.delicious.network"

    buildFeatures {
        buildConfig = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {

    with(projects) {
        implementation(core.base)
    }

    libs.apply {
        implementation(kotlinx.datetime)
        api(kotlinx.serialization.json)
        implementation(logging.interceptor)
        api(retrofit)
        api(retrofit.kotlin.serialization)
        testImplementation(junit)
        androidTestImplementation(junit.ext)
    }

}