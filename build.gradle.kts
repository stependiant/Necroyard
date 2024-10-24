plugins {
    id("java")
}

group = "step"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

}

dependencies {
    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("net.minestom:minestom-snapshots:bcb0301fb1")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}