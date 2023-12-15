plugins {
    libs.plugins.apply {
        alias(libs.plugins.kotlinx.serialization) apply false
        alias(libs.plugins.secrets) apply false
        alias(android.application) apply false
        alias(kotlin.parcelize) apply false
//        alias(android.library) apply false
//        alias(kotlin.android) apply false
        alias(hilt.android) apply false
        alias(kotliner) apply false
        alias(detekt) apply false
        alias(ksp) apply false
    }
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
}

// Run it with: gradle assembleRelease -PcomposeCompilerReports=true
subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            if (project.findProperty("composeCompilerReports") == "true") {
                freeCompilerArgs += listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${project.layout.buildDirectory.asFile.get()}/compose_compiler"
                )
            }
            if (project.findProperty("composeCompilerMetrics") == "true") {
                freeCompilerArgs += listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${project.layout.buildDirectory.asFile.get()}/compose_compiler"
                )
            }
        }
    }
}