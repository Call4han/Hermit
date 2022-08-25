group = "org.example"
version = "1.0-SNAPSHOT"
plugins {
    id("java")
}

group = "me.callahandevelopment"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {

    compileOnly("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT")
}
