plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.weather"
    compileSdk = 34

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    packaging {
        resources.excludes.add("META-INF/LICENSE.md")
        resources.excludes.add("META-INF/LICENSE-notice.md")
    }


    defaultConfig {
        applicationId = "com.example.weather"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {

        release {
            isCrunchPngs= false
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation("androidx.test:core-ktx:1.5.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Retrofit Dependency
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")

    // Room Dependency
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-paging:$roomVersion")

    // Dagger Hilt Dependency
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")

    // Coroutines Dependency
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Nav graph Dependency
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

    val lifecycle_version = "2.7.0"
    // ViewModel Dependency
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // LiveData Dependency
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    // Lifecycles only Dependency (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")


    implementation("androidx.collection:collection-ktx:1.3.0")

    // Timber Logger Dependency
    implementation("com.jakewharton.timber:timber:5.0.1")

    testImplementation ("com.google.truth:truth:1.1.3")

    // Multidex Dependency
    implementation ("androidx.multidex:multidex:2.0.1")


    //lottie
    implementation("com.airbnb.android:lottie:4.1.0")

}

kapt {
    correctErrorTypes = true

}

hilt {
    enableAggregatingTask = false
}


