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
        System.out.println("************${project.testExt}************")
        System.out.println("************${project.testExt.modelName}************")
        System.out.println("************${project.extensions.findByName("testExt") == null}************")
        System.out.println("************${project.extensions.findByName("testExt").hasProperty("modelName")}************")
        System.out.println("************${project.extensions.findByName("testExt").getProperties().get("modelName")}************")
        System.out.println("************${project.extensions.getByName("testExt").modelName}************")

        def android = project.extensions.getByType(AppExtension)
        if (project.plugins.hasPlugin(AppPlugin)) {
            android.applicationVariants.all { variant ->
                def name = variant.name
                def versionName = variant.versionName
                def versionCode = variant.versionCode

                System.out.println("************${name}************")
                System.out.println("************${versionName}************")
                System.out.println("************${versionCode}************")
            }
        }
        project.task('myTask') {
            doLast {
                println "************Hi this is micky's plugin************"
            }
        }

        System.out.println("************plugin end************")
    }
}