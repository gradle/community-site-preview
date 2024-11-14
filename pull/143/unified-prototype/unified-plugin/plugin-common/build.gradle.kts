@file:Suppress("UnstableApiUsage")

plugins {
    `kotlin-dsl`
    id("build-logic.publishing")
    groovy // For spock testing
    `java-test-fixtures`
}

description = "Common APIs and implementation classes shared by the ecosystem specific declarative prototypes"

dependencies {
    implementation("commons-io:commons-io:2.15.1")
    implementation(gradleApi())
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useSpock("2.2-groovy-3.0")

            dependencies {
                implementation("commons-io:commons-io:2.15.1")
                implementation(testFixtures(project()))
            }
        }

        val integTest by registering(JvmTestSuite::class) {
            useSpock("2.2-groovy-3.0")

            dependencies {
                implementation("commons-io:commons-io:2.15.1")
                implementation(project(":plugin-jvm"))
                implementation(testFixtures(project()))
            }
        }

        tasks.getByPath("check").dependsOn(integTest)
    }
}

gradlePlugin {
    plugins {
        create("common") {
            id = "org.gradle.experimental.declarative-common"
            displayName = "Common Experimental Declarative Plugin"
            description = "Experimental declarative plugin containing common code shared by the Android, JVM, and KMP prototype plugins"
            implementationClass = "org.gradle.api.experimental.common.CommonPlugin"
            tags = setOf("declarative-gradle")
        }
    }
}
