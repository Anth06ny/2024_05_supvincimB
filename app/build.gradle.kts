plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.amonteiro.a2024_05_supvincimb"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.amonteiro.a2024_05_supvincimb"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //Utilisation générale
    //Utilisation générale
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:+")
//LifeCycleScope
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.+")
//ViewModelScope
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.+")

//LifeCycleScope
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.+")
//ViewModelScope
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.+")


    //image url
    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")


    //Requete web
    implementation("com.squareup.okhttp3:okhttp:+")
    //JSon
    implementation("com.google.code.gson:gson:+")



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}