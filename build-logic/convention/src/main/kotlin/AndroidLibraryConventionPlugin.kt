import com.delicious.recipes.androidGradle
import com.delicious.recipes.configureKotlinAndroid
import com.delicious.recipes.disableUnnecessaryAndroidTests
import com.delicious.recipes.libs
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.run {
            applyPlugins()
            applyDependencies()
            androidGradle {
                configureKotlinAndroid(this)
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk =
                    Integer.parseInt(libs.findVersion("applicationTargetSdkVersion").get().toString())
            }
            extensions.configure<LibraryAndroidComponentsExtension> {
                disableUnnecessaryAndroidTests(project)
            }
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
//            apply("recipes.android.detekt")
            apply("recipes.android.ktlint")
        }
    }

    private fun Project.applyDependencies() {
        dependencies {
            add("testImplementation", kotlin("test"))
            add("androidTestImplementation", kotlin("test"))
            add("testImplementation" , libs.findBundle("test").get())
            add("androidTestImplementation" , libs.findBundle("androidTest").get())
        }
    }
}