repositories {
    mavenCentral()
}

plugins {
    java
}

group = "vtb.learn"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

defaultTasks("clean", "build")

val lombokVersion = "1.18.34"

dependencies {
//    testImplementation(platform("org.junit:junit-bom:5.9.1"))
//    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.projectlombok:lombok:$lombokVersion")
    implementation("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}