pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "MyDeliciousRecipe"
include(":app")
include(":core:network")
include(":core:systemdesign")
include(":core:ui")
include(":core:utility")
include(":core:database")
include(":core:analytics")
include(":core:datastore")
include(":home:home-ui")
