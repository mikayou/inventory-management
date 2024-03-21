import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    maven("https://maven.aliyun.com/repository/public")
    maven("https://repo.spring.io/plugins-release")
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-web:3.1.0")
    // Sa-Token 权限认证，在线文档：https://sa-token.cc
    implementation ("cn.dev33:sa-token-spring-boot3-starter:1.34.0")
    implementation("com.mysql:mysql-connector-j:8.0.32")

    implementation("io.github.swagger2markup:swagger2markup:1.3.4")
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.1.0")
    implementation("org.mybatis:mybatis:3.5.13")
    implementation("org.mybatis:mybatis-spring:3.0.2")
    implementation("org.springframework:spring-tx:6.0.6")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.1")
    implementation("org.springframework.boot:spring-boot-devtools:3.0.4")
    implementation("io.github.swagger2markup:swagger2markup-builder:1.3.4")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

