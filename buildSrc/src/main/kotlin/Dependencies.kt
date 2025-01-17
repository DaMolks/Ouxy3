object Versions {
    // SDK et outils
    const val compileSdk = 34
    const val minSdk = 23
    const val targetSdk = 34

    // Kotlin
    const val kotlin = "1.9.22"
    const val coroutines = "1.7.3"

    // AndroidX
    const val core = "1.12.0"
    const val appcompat = "1.6.1"
    const val activity = "1.8.2"
    const val fragment = "1.6.2"
    const val lifecycle = "2.7.0"
    const val navigation = "2.7.6"
    const val room = "2.6.1"

    // UI
    const val material = "1.11.0"
    const val constraintLayout = "2.1.4"
    const val swipeRefresh = "1.1.0"
    const val lottie = "6.3.0"

    // DI
    const val koin = "3.5.3"

    // Test
    const val junit = "4.13.2"
    const val androidxTest = "1.5.0"
    const val espresso = "3.5.1"
    const val mockk = "1.13.9"
}

object Deps {
    // Kotlin
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // AndroidX
    const val coreKtx = "androidx.core:core-ktx:${Versions.core}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val activity = "androidx.activity:activity-ktx:${Versions.activity}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"

    // Lifecycle
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"

    // Navigation
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    // Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    // UI
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"

    // Koin
    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"

    // Test
    const val junit = "junit:junit:${Versions.junit}"
    const val androidxTestCore = "androidx.test:core:${Versions.androidxTest}"
    const val androidxTestRunner = "androidx.test:runner:${Versions.androidxTest}"
    const val androidxTestRules = "androidx.test:rules:${Versions.androidxTest}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
}