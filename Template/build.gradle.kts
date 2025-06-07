plugins {
	kotlin("jvm") version "1.9.25"
	id("com.github.johnrengelman.shadow") version "7.0.0"
	jacoco
}

group = "com.template"
version = "0.0.1"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

allprojects{
	repositories {
		mavenCentral()
	}
	tasks.withType<Test> {
		useJUnitPlatform()
	}
}

repositories {
	mavenCentral()
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks {
	clean {
		dependsOn(":client:clean")
		delete(file("${project.projectDir}/executables"))
	}
	build {
		actions.clear()
		dependsOn(clean)
		dependsOn(":client:bundle")
		dependsOn(":domain:assemble")
		dependsOn(":infrastructure:shadowJar")
	}
}