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

//    buildFeatures {
//        buildConfig = true
//    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

secrets {
    // A properties file containing default secret values. This file can be checked in version
    // control.
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {

    with(projects) {
//        implementation(core.k)
//        implementation(core.common)
    }

    libs.apply {
        implementation(kotlinx.datetime)
        implementation(kotlinx.serialization.json)
        implementation(logging.interceptor)
        implementation(retrofit)
        implementation(chucker)
        implementation(retrofit.kotlin.serialization)

        testImplementation(junit)
        androidTestImplementation(junit.ext)
    }

}