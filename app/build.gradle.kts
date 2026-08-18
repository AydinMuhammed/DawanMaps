plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "fr.dawan.dawanmaps"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "fr.dawan.dawanmaps"
        minSdk = 25
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.osmdroid)        // Carte OpenStreetMap
    implementation(libs.retrofit)        // Appels HTTP vers l'API Dawan
    implementation(libs.converter.gson)  // JSON -> objets Java
    implementation(libs.gson)            // Moteur JSON sous-jacent
    implementation(libs.room.runtime)    // SQLite de haut niveau
    annotationProcessor(libs.room.compiler) // Générateur de code Room (important)
    androidTestImplementation(libs.fragment.testing) // Tests d'UI avec fragments
}