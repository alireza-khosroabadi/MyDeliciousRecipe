import com.delicious.recipes.androidGradle
import com.delicious.recipes.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.run {
            applyPlugins()
            androidGradle {
                configureAndroidCompose(this)
            }
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            apply("com.android.library")
        }
    }

}