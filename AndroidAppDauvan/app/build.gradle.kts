plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.latest_try_dauvan_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.latest_try_dauvan_app"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // Firebase BOM - ensures all Firebase dependencies are compatible
    implementation(platform("com.google.firebase:firebase-bom:32.2.0"))

    // Firebase Authentication and other Firebase dependencies
    implementation("com.google.firebase:firebase-auth")

    // Additional dependencies
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.runtime)
    implementation(libs.cardview)
    implementation(libs.play.services.tasks)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.volley)
}

// Apply the Google services plugin
apply(plugin = "com.google.gms.google-services")
