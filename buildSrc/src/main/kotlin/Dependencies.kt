object Versions {
    const val compileSdk = 34
    const val minSdk = 23
    const val targetSdk = 34
    
    const val kotlin = "1.9.22"
    const val coroutines = "1.7.3"
    const val core = "1.12.0"
    const val appcompat = "1.6.1"
    const val material = "1.11.0"
    const val constraintLayout = "2.1.4"
    const val navigation = "2.7.6"
    const val room = "2.6.1"
    const val lifecycle = "2.7.0"
    const val dynamicFeature = "1.0.0"
    const val koin = "3.5.3"
    const val lottie = "6.3.0"
}

object Libs {
    // Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // AndroidX
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    // Navigation
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationDynamic = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"

    // Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    // Lifecycle
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

    // Dynamic Feature Module
    const val playCore = "com.google.android.play:core:${Versions.dynamicFeature}"

    // Koin
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"

    // Lottie
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
}

object TestLibs {
    const val junit = "junit:junit:4.13.2"
    const val androidJunit = "androidx.test.ext:junit:1.1.5"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.5.1"
    const val roomTesting = "androidx.room:room-testing:${Versions.room}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
}