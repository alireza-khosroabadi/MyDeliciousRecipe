import com.delicious.recipes.androidGradle
import com.delicious.recipes.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins()
            applyDependencies()
            androidGradle {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            apply("recipes.android.library")
            apply("recipes.android.library.compose")
            apply("recipes.android.hilt")
        }
    }

    private fun Project.applyDependencies() {
        dependencies {
//            add("implementation", project(":core:analytics"))

            add("testImplementation", kotlin("test"))
//            add("testImplementation", project(":core:testing"))
            add("androidTestImplementation", kotlin("test"))
//            add("androidTestImplementation", project(":core:testing"))

            add("implementation", libs.findLibrary("coil.kt").get())
            add("implementation", libs.findLibrary("coil.kt.compose").get())

            add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
            add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
            add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())

            add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
        }
    }
}