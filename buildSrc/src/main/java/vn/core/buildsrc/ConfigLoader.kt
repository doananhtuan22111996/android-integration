package vn.core.buildsrc

import org.gradle.api.Project
import java.io.FileInputStream
import java.util.Properties

object ConfigLoader {
    fun load(project: Project, flName: String): Properties = Properties().apply {
        load(FileInputStream(project.rootProject.file("env.$flName.properties")))
    }
}
