import com.delicious.recipes.RecipesBuildType

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    libs.plugins.recipes.android.apply {
        alias(application)
        alias(application.compose)
        alias(hilt)
    }
}

android {
    namespace = libs.versions.recipesApplicationId.get()
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildTypes {
            debug {
                applicationIdSuffix = RecipesBuildType.DEBUG.applicationIdSuffix
            }
            release {
                isMinifyEnabled = true
                applicationIdSuffix = RecipesBuildType.RELEASE.applicationIdSuffix
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

//                signingConfig = signingConfigs.getByName("release")
            }
        }
    }
}

dependencies {
    projects.apply {
        core.apply {
            implementation(utility)
            implementation(systemdesign)
            implementation(ui)
        }
        implementation(home.homeUi)
        implementation(favoriteRecipe.favoriteUi)
        implementation(recipe.recipeUi)
    }

    libs.apply {
        androidx.apply {
            implementation(activity.compose)
            implementation(appcompat)
            implementation(core.ktx)
            implementation(core.splashscreen)
            implementation(lifecycle.runtimeCompose)
            implementation(hilt.navigation.compose)
            implementation(navigation.compose)
            implementation(window.manager)
            implementation(compose.material3.windowSizeClass)
        }
        implementation(accomponist.adaptive)
        implementation(kotlinx.coroutines.guava)
        implementation(coil.kt)

        testImplementation(junit)
        androidTestImplementation(junit.ext)
    }
}
