plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "Template"
include("domain")
include("infrastructure")
include("client")