package com.delicious.recipes

/**
 * This is shared between :app module to provide configurations type safety.
 */
enum class RecipesBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE
}
