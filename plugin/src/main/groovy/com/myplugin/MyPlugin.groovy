package com.myplugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

public class MyPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        System.out.println("************plugin start************")

//        def classTransform = new MyClassTransform(project)
//        android.registerTransform(classTransform);

        project.extensions.create("testExt", MyPluginTestClass)
        println "************${project.name}************"
        System.out.println("************${project.testExt}************")
        System.out.println("************${project.testExt.modelName}************")
        System.out.println("************${project.extensions.findByName("testExt") == null}************")
        System.out.println("************${project.extensions.findByName("testExt").hasProperty("modelName")}************")

        project.afterEvaluate {
            println "************afterEvaluate************"
            println "************${project.testExt.modelName} afterEvaluate************"
            def preBuild = project.tasks.findByName("preBuild")
            if (preBuild) {
                def myTask = project.task('myTask') {
                    doLast {
                        println "************task test************"
                        println "************${project.testExt.modelName}************"
                    }
                }
                myTask.dependsOn(preBuild.getTaskDependencies().getDependencies(preBuild))
//                myTask.dependsOn(preBuild.getDependsOn())
                preBuild.dependsOn(myTask)
            } else {
                println "************no preBuild************"
            }
        }

        def android = project.extensions.getByType(AppExtension)
        if (project.plugins.hasPlugin(AppPlugin)) {
            android.applicationVariants.all { variant ->
                System.out.println("************${project.extensions.findByName("testExt").getProperties().get("modelName")}************")
                System.out.println("************${project.extensions.getByName("testExt").modelName}************")
                def name = variant.name.capitalize()
                def buildType = variant.buildType.name.capitalize()
                def versionName = variant.versionName
                def versionCode = variant.versionCode

                System.out.println("************${name}************")
                System.out.println("************${buildType}************")
                System.out.println("************${versionName}************")
                System.out.println("************${versionCode}************")
            }
        }

        System.out.println("************plugin end************")
    }
}