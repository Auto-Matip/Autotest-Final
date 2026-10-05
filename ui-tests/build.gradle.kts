plugins {
    id("java")
    id("io.qameta.allure") version "3.2.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

allure {
    version.set("2.29.0")
}

dependencies {
    testImplementation(project(":common"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.assertj:assertj-core:3.27.7")
    implementation("io.rest-assured:rest-assured:5.5.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.22.1")
    implementation("org.seleniumhq.selenium:selenium-java:4.46.0")
    implementation("com.codeborne:selenide:7.17.0")
    implementation("io.qameta.allure:allure-rest-assured:2.29.0")
    testImplementation("io.qameta.allure:allure-selenide:2.29.0")

}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Test>("uitest") {
    group = "ui"
    useJUnitPlatform {
        includeTags("ui")
    }
}