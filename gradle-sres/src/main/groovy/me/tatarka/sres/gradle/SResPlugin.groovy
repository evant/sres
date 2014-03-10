package me.tatarka.sres.gradle
import me.tatarka.sres.SRes
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.ProjectConfigurationException
import org.gradle.api.internal.file.DefaultSourceDirectorySet
import org.gradle.api.internal.file.FileResolver

import javax.inject.Inject
/**
 * Created by evan on 3/1/14.
 */
class SResPlugin implements Plugin<Project> {
    private fileResolver

    @Inject
    public SResPlugin(FileResolver fileResolver) {
        this.fileResolver = fileResolver
    }

    @Override
    void apply(Project project) {
        if (!(project.plugins.hasPlugin('android') || project.plugins.hasPlugin ('android-library'))) {
            throw new ProjectConfigurationException(
                    "the 'android' or 'android-library' plugin must be applied.", null
            );
        }

        def resBuildDir = project.file("$project.buildDir/sres/res")
        def codeBuildDir = project.file("$project.buildDir/sres/java")

        def sresGenerate = project.task("sresGenerate");
        def sres = new SRes();

        project.android.sourceSets.all { sourceSet ->
            def dirSet = new DefaultSourceDirectorySet("$sourceSet.displayName sres source", fileResolver)
            dirSet.filter { include "**/*.sres" }
            dirSet.srcDirs = [project.file("src/$sourceSet.name/sres")]
            sourceSet.ext.sres = dirSet
        }

        project.afterEvaluate {
            def isLibrary = project.plugins.hasPlugin('android-library')
            def variants = (isLibrary ?
                    project.android.libraryVariants :
                    project.android.applicationVariants) + project.android.testVariants

            def packageName = project.android.defaultConfig.packageName
            if (packageName == null) throw new ProjectConfigurationException("You must define android.defaultConfig.packageName", null)

            project.android.sourceSets.each { set ->
                def setResBuildDir = project.file("$resBuildDir/$set.name")
                def setCodeBuildDir = project.file("$codeBuildDir/$set.name")

                set.res.srcDirs += setResBuildDir
                set.java.srcDirs += setCodeBuildDir

                def task = project.task("sres${set.name.capitalize()}Generate") << {
                    set.sres.srcDirs.each { dir ->
                        if (dir.isDirectory()) {
                            sres.generateAll(packageName, dir.toPath(), setResBuildDir.toPath(), setCodeBuildDir.toPath())
                        }
                    }
                }

                sresGenerate.dependsOn(task)
            }

            variants.each { var -> var.mergeResources.dependsOn(sresGenerate) }
        }
    }
}
