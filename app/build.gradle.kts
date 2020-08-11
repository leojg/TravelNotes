plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Versions.TARGET_SDK_VERSION)
    buildToolsVersion(Versions.BUILD_TOOLS_VERSION)

    defaultConfig {
        applicationId = "lgcode.me.travelnotes"
        minSdkVersion(Versions.MIN_SDK_VERSION)
        targetSdkVersion(Versions.TARGET_SDK_VERSION)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
        }
    }
    dataBinding.isEnabled = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN_VERSION}")

    // Sdk and tools dependencies
    implementation("androidx.appcompat:appcompat:${Versions.APPCOMPAT_VERSION}")
    implementation("com.google.android.material:material:${Versions.MATERIAL_VERSION}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT_VERSION}")
    implementation("androidx.recyclerview:recyclerview:${Versions.RECYCLER_VIEW_VERSION}")
    implementation("androidx.core:core-ktx:${Versions.CORE_KTX_VERSION}")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.CORRUTINES_VERSION}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.CORRUTINES_VERSION}")

    //koin
    implementation("org.koin:koin-android:${Versions.KOIN_VERSION}")
    implementation("org.koin:koin-androidx-viewmodel:${Versions.KOIN_VERSION}")

    //glide
    implementation("com.github.bumptech.glide:glide:${Versions.GLIDE_VERSION}")
    kapt("com.github.bumptech.glide:compiler:${Versions.GLIDE_VERSION}")

    //room
    implementation("androidx.room:room-runtime:${Versions.ROOM_VERSION}")
    kapt("androidx.room:room-compiler:${Versions.ROOM_VERSION}")

    testImplementation("junit:junit:${Versions.JUNIT_VERSION}")
    androidTestImplementation("androidx.test:runner:${Versions.ANDROIDX_RUNNER_VERSION}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.ANDROIDX_ESPRESSO_VERSION}")
}
