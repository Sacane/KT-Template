import com.github.gradle.node.npm.task.NpmTask

plugins {
  id("com.github.node-gradle.node") version "3.5.1"
}

group = "com.gloryConnect.app.client"

tasks {
  npmInstall {
    doNotTrackState("node_modules")
    ignoreExitValue.set(false)
    outputs.dir(file("${rootProject.projectDir}/client/node_modules"))
  }

  val build = register<NpmTask>("build") {
    dependsOn(npmInstall)
    npmCommand.set(listOf("run", "build"))
  }

  register<Copy>("bundle") {
    dependsOn(build)
    inputs.dir(file("${rootProject.projectDir}/client/dist/glory-connect-client/browser"))
    outputs.dir(file("${rootProject.projectDir}/infrastructure/src/main/resources/static"))
    from("${rootProject.projectDir}/client/dist/glory-connect-client/browser")
    into("${rootProject.projectDir}/infrastructure/src/main/resources/static")
  }

  register<Delete>("clean") {
    delete(file("${rootProject.projectDir}/client/dist"))
    delete(file("${rootProject.projectDir}/infrastructure/src/main/resources/static"))
  }
}
