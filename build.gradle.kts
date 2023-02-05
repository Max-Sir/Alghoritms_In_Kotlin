import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    id("io.gitlab.arturbosch.detekt") version "1.14.0"
    application
}

group = "me.maxim"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://dl.bintray.com/kotest/kotest")
    maven(url = "https://plugins.gradle.org/m2/")
}

dependencies {
    val versions = mapOf(
        "kotlinTest" to "1.3.72",
        "rxJava" to "2.2.20",
        "kotlinStdLib" to "1.3.72",
        "kotlinCoroutinesCore" to "1.3.7",
        "kotlinCoroutinesRx2" to "1.3.7",
        "kotestAssertionsCore" to "4.2.2",
        "kotestRunnerJunit5" to "4.2.2",
        "junit" to "4.13.1"
    )
    val libs = mapOf(
        "kotlinTest" to kotlin("test"),
        "kotlinReflect" to "org.jetbrains.kotlin:kotlin-reflect",
        "rxJava" to "io.reactivex.rxjava2:rxjava",
        "kotlinStdLib" to "org.jetbrains.kotlin:kotlin-stdlib-jdk7",
        "kotlinCoroutinesCore" to "org.jetbrains.kotlinx:kotlinx-coroutines-core",
        "kotlinCoroutinesRx2" to "org.jetbrains.kotlinx:kotlinx-coroutines-rx2",
        "kotestAssertionsCore" to "io.kotest:kotest-assertions-core",
        "kotestRunnerJunit5" to "io.kotest:kotest-runner-junit5",
        "junit" to "junit:junit"
    )

    testImplementation(libs["kotlinTest"]!!)
    implementation("${libs["kotlinReflect"]}:${versions["kotlinTest"]}")
    implementation("${libs["rxJava"]}:${versions["rxJava"]}")
    implementation("${libs["kotlinStdLib"]}:${versions["kotlinStdLib"]}")
    implementation("${libs["kotlinCoroutinesCore"]}:${versions["kotlinCoroutinesCore"]}")
    implementation("${libs["kotlinCoroutinesRx2"]}:${versions["kotlinCoroutinesRx2"]}")
    testImplementation("${libs["kotestAssertionsCore"]}:${versions["kotestAssertionsCore"]}")
    testImplementation("${libs["kotestRunnerJunit5"]}:${versions["kotestRunnerJunit5"]}")
    testImplementation("${libs["junit"]}:${versions["junit"]}")
    implementation("io.gitlab.arturbosch.detekt:detekt-cli:1.12.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

detekt {
    autoCorrect = true
    input = files("src/main/kotlin")
    reports {
        xml.enabled = true
        html.enabled = false
        txt.enabled = false
    }
}

tasks.withType<Detekt> {
    group = "verification"
    description = "Run static code analysis with detekt"
}

application {
    mainClass.set("MainKt")
}