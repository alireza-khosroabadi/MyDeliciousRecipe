plugins {
    `kotlin-dsl`
}

group = "${libs.versions.recipesApplicationId}.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
    compileOnly(libs.ktlint.kotlinter)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradlePlugin)
    compileOnly(libs.firebase.performance.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "recipes.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "recipes.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidHilt") {
            id = "recipes.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidLibrary") {
            id = "recipes.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "recipes.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeature") {
            id = "recipes.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidRoom") {
            id = "recipes.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("androidDetekt") {
            id = "recipes.android.detekt"
            implementationClass = "AndroidDetektConventionPlugin"
        }
        register("androidKtlint") {
            id = "recipes.android.ktlint"
            implementationClass = "AndroidKotlinterConventionPlugin"
        }
        register("androidFirebase") {
            id = "recipes.android.application.firebase"
            implementationClass = "AndroidApplicationFirebaseConventionPlugin"
        }
    }
}
