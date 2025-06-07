import com.github.jengelman.gradle.plugins.shadow.transformers.PropertiesFileTransformer
plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("plugin.jpa") version "1.9.25"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    kotlin("plugin.noarg") version "1.9.25"
}

group = "com.template.infrastructure"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation(project(mapOf("path" to ":domain")))
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("com.h2database:h2")
}
allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks {
    shadowJar {
        mergeServiceFiles()
        archiveBaseName.set("TemplateApp")
        archiveClassifier.set("")
        archiveVersion.set(project.version.toString())
        destinationDirectory.set(file("$rootDir/executables"))
        append("META-INF/spring.handlers")
        append("META-INF/spring.schemas")
        append("META-INF/spring.tooling")
        transform(
            PropertiesFileTransformer().apply {
                paths = mutableListOf("META-INF/spring.factories")
                mergeStrategy = "append"
            }
        )
        from("infra/src/main/resources/application.properties") {
            into("executables")
        }
        setProperty("zip64", true)
    }
    jar{
        manifest {
            attributes["Main-Class"] = "com.template.app.infrastructure.TemplateApplicationKt"
        }
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
    processResources {
        mustRunAfter(":client:bundle")
    }
}