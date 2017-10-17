package com.myplugin

import org.gradle.api.Plugin
import org.gradle.api.Project


public class MyPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        System.out.println("************plugin start************")
        project.task('myTask') {
            doLast {
                println "************Hi this is micky's plugin************"
            }
        }
        System.out.println("************plugin end************")
    }
}