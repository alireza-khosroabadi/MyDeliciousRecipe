import com.delicious.recipes.applicationGradle
import com.delicious.recipes.configureKotlinAndroid
import com.delicious.recipes.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.run {
            applyPlugins()
            applicationGradle {
                configureKotlinAndroid(this)
                defaultConfig {
                    targetSdk =
                        Integer.parseInt(
                            libs.findVersion("applicationTargetSdkVersion").get().toString()
                        )
                    compileSdk =
                        Integer.parseInt(
                            libs.findVersion("applicationCompileSdkVersion").get().toString()
                        )

                    versionCode =
                        Integer.parseInt(
                            libs.findVersion("applicationVersionCode").get().toString()
                        )

                    versionName = libs.findVersion("applicationVersionName").get().toString()
                }
            }
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.android")
        }
    }
}
