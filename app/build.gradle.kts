plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt.plugins)
    id("kotlin-kapt")

}

android {
    namespace = "com.example.quotesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.quotesapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    kapt {
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //lifecycle
    implementation(libs.lifecycle)
    //navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    //multidex
    implementation(libs.multidex)
    //dimens
    implementation(libs.dimens.sdp)
    implementation(libs.dimens.ssp)
    //glide library
    implementation(libs.glide.library)
    //gson library
    implementation(libs.gson.version)
    //dagger Hilt
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    //lottie animation
    implementation(libs.lottie.animation)
    //room database
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)

}

kapt {
    correctErrorTypes = true
    useBuildCache = false
}