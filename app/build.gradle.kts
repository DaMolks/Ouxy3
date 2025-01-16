plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.damolks.ouxy3"
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.damolks.ouxy3"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Kotlin
    implementation(Libs.kotlinStdLib)
    implementation(Libs.coroutinesCore)
    implementation(Libs.coroutinesAndroid)

    // AndroidX
    implementation(Libs.core)
    implementation(Libs.appcompat)
    implementation(Libs.material)
    implementation(Libs.constraintLayout)

    // Navigation
    implementation(Libs.navigationFragment)
    implementation(Libs.navigationUi)
    implementation(Libs.navigationDynamic)

    // Room
    implementation(Libs.roomRuntime)
    implementation(Libs.roomKtx)
    kapt(Libs.roomCompiler)

    // Lifecycle
    implementation(Libs.lifecycleViewModel)
    implementation(Libs.lifecycleLiveData)
    implementation(Libs.lifecycleRuntime)

    // Dynamic Feature
    implementation(Libs.playCore)

    // Koin
    implementation(Libs.koinAndroid)

    // Lottie
    implementation(Libs.lottie)

    // Testing
    testImplementation(TestLibs.junit)
    androidTestImplementation(TestLibs.androidJunit)
    androidTestImplementation(TestLibs.espressoCore)
    testImplementation(TestLibs.roomTesting)
    testImplementation(TestLibs.coroutinesTest)
}