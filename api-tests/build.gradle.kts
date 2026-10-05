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
    testImplementation("org.junit.jupiter:junit-jupiter:5.12.2")
    testImplementation("org.junit.platform:junit-platform-suite:1.12.2")
    implementation("io.qameta.allure:allure-rest-assured:2.29.0")
    testImplementation("io.qameta.allure:allure-selenide:2.29.0")

}

tasks.test {
    useJUnitPlatform()
}


val CrTest by tasks.registering(Test::class) {
    group = "apiTest"
    useJUnitPlatform {
        includeTags("Create")
    }
}


val GetAllGoodsTest by tasks.registering(Test::class) {
    group = "apiTest"
    useJUnitPlatform {
        includeTags("GetGoods")
    }
    mustRunAfter(CrTest)
}
val GetOneGoodTest by tasks.registering(Test::class) {
    group = "apiTest"
    useJUnitPlatform {
        includeTags("GetGoodById")
    }
    mustRunAfter(CrTest, GetAllGoodsTest)
}
val ChangeTest by tasks.registering(Test::class) {
    group = "apiTest"
    useJUnitPlatform {
        includeTags("ChangeGood")
    }
    mustRunAfter(CrTest, GetAllGoodsTest, GetOneGoodTest)
}
val DeleteTest by tasks.registering(Test::class) {
    group = "apiTest"
    useJUnitPlatform {
        includeTags("DeleteGood")
    }
    mustRunAfter(CrTest, GetAllGoodsTest, GetOneGoodTest, ChangeTest)
}

tasks.register<Test>("runTags") {
    group = "apiTest"
    description = "Запуск тестов по очереди"

    dependsOn(CrTest, GetAllGoodsTest, GetOneGoodTest, ChangeTest, DeleteTest)
}
