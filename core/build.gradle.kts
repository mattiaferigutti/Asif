plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  kotlin("kapt")
  id("com.google.dagger.hilt.android")
}

android {
  namespace = "com.mattiaferigutti.core"
  compileSdk = 33

  defaultConfig {
    minSdk = 24
    targetSdk = 33

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    // Flag to enable support for the new language APIs
    // Flag to enable support for the new language APIs

    // For AGP 4.1+
    isCoreLibraryDesugaringEnabled = true
    // For AGP 4.0
    // coreLibraryDesugaringEnabled = true

    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {

  implementation("androidx.core:core-ktx:1.9.0")
  implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("com.google.android.material:material:1.9.0")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

  val room_version = "2.5.2"
  implementation("androidx.room:room-ktx:$room_version")
  kapt("androidx.room:room-compiler:$room_version")

  // Desugaring
  coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")

  // Hilt
  implementation("com.google.dagger:hilt-android:2.44.2")
  kapt("com.google.dagger:hilt-android-compiler:2.44.2")
}

// Allow references to generated code
kapt {
  correctErrorTypes = true
}
