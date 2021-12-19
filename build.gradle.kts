plugins {
    kotlin("jvm") version "1.6.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src/main")
        }
        test{
            java.srcDirs("src/test")
        }
    }

    wrapper {
        gradleVersion = "7.3"
    }
}
