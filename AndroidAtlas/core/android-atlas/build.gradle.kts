import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    id("com.vanniktech.maven.publish") version "0.30.0"
    id("com.gradleup.nmcp") version "0.0.7"
}

android {
    namespace = "com.atlassoftware.libs"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }


}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.10")
    implementation("org.apache.commons:commons-text:1.10.0")
}


mavenPublishing {
    coordinates(
        groupId = "dev.atlassoftware.libs",
        artifactId = "android-atlas",
        version = "1.0.1"
    )

    pom {
        name = "android-atlas"
        description = "This project is the compilation of various utility and helper classes which can be used directly in Android projects with minimum boilerplate code"
        url = "https://github.com/atlas-software-dev/android-atlas"

        licenses {
            license {
                name = "MIT License"
                url = "https://github.com/atlas-software-dev/android-atlas/blob/main/LICENSE"
            }
        }

        developers {
            developer {
                id = "paulo.albertklik"
                name = "Paulo Figueiro"
                email = "paulo.albertklik@gmail.com"
                url = "https://albertklik.github.dio/"
            }
        }

        scm {
            url = "https://github.com/atlas-software-dev/android-atlas"
            connection = "scm:git@github.com:atlas-software-dev/android-atlas.git"
            developerConnection = "scm:git:git@github.com:albertklik/android-atlas.git"
        }
    }

    // Configure publishing to Maven Central
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    // Enable GPG signing for all publications
    signAllPublications()
}





