plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.damolks.ouxy3"
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.damolks.ouxy3"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    // Kotlin
    implementation(Deps.kotlin)
    implementation(Deps.coroutinesCore)
    implementation(Deps.coroutinesAndroid)

    // AndroidX
    implementation(Deps.coreKtx)
    implementation(Deps.appcompat)
    implementation(Deps.activity)
    implementation(Deps.fragment)

    // Lifecycle
    implementation(Deps.lifecycleViewModel)
    implementation(Deps.lifecycleLiveData)

    // Navigation
    implementation(Deps.navigationFragment)
    implementation(Deps.navigationUi)

    // Room
    implementation(Deps.roomRuntime)
    implementation(Deps.roomKtx)
    kapt(Deps.roomCompiler)

    // UI
    implementation(Deps.material)
    implementation(Deps.constraintLayout)
    implementation(Deps.swipeRefresh)
    implementation(Deps.lottie)

    // Koin
    implementation(Deps.koinCore)
    implementation(Deps.koinAndroid)

    // Tests unitaires
    testImplementation(Deps.junit)
    testImplementation(Deps.mockk)
    testImplementation(Deps.coroutinesTest)

    // Tests d'intégration
    androidTestImplementation(Deps.androidxTestCore)
    androidTestImplementation(Deps.androidxTestRunner)
    androidTestImplementation(Deps.androidxTestRules)
    androidTestImplementation(Deps.espressoCore)
    androidTestImplementation(Deps.mockkAndroid)

    // Desugar pour java.time
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
}