plugins {
//    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.android.application")
  //  id("com.google.gms.google-services")
    id ("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.example.chat_fluent"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.chat_fluent"
        minSdk = 24
        targetSdk = 35
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3" // or latest
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.navigation.compose)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.navigation:navigation-compose:2.8.4")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //More Icons
    implementation("androidx.compose.material:material-icons-extended:1.7.8")
    implementation("androidx.compose.material3:material3:1.1.1")

//    //Firebase
//    implementation(platform("com.google.firebase:firebase-bom:33.12.0"))
//    implementation("com.google.firebase:firebase-analytics")
//    implementation("com.google.firebase:firebase-auth")
//    implementation("com.google.firebase:firebase-firestore")
//
//    //Gemini
//    implementation("com.google.ai.client.generativeai:generativeai:0.9.0")
//    implementation(platform("io.github.jan-tennert.supabase:bom:3.1.4"))
//    implementation("io.github.jan-tennert.supabase:postgrest-kt")
//    implementation("io.github.jan-tennert.supabase:auth-kt")
//    implementation("io.github.jan-tennert.supabase:realtime-kt")
//    implementation("io.ktor:ktor-client-core:3.1.2")
//    implementation("io.ktor:ktor-client-cio:3.1.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")


    // For voice input (Speech-to-Text)
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // For recording audio (optional)
    implementation("com.google.accompanist:accompanist-permissions:0.32.0")

// Retrofit + Networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

// Coroutines

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

// ViewModel + Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
//Json
    implementation("com.google.code.gson:gson:2.10.1")

    // OpenAI (Retrofit)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

}

