package com.grechur.buildsrc
import org.gradle.api.Plugin
import org.gradle.api.Project

class OurPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        println ("----------------------------------")
        println ("yao cheng gong a ")
    }
}