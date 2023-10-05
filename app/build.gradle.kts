plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // Hilt
    id ("kotlin-kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.architecturehiltcoroutinesmvvm"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.architecturehiltcoroutinesmvvm"
        minSdk = 26
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // SDP,SSP
    implementation ("com.intuit.sdp:sdp-android:1.1.0")
    implementation ("com.intuit.ssp:ssp-android:1.1.0")
    // Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")


    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")

    // LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")

    // Retrofit & Gson converter
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    // Okhttp
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")

    // Lifecycle
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.fragment:fragment-ktx:1.6.1")

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.14.2")

    // Coroutine
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Timber
    implementation ("com.jakewharton.timber:timber:5.0.1")
}
