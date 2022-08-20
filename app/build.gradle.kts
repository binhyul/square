plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

repositories {
    maven {
        setUrl("../repository")
    }
    google()
    mavenCentral()
}

android {
    compileSdk = Versions.Android.compileSdkVersion

    defaultConfig {
        applicationId = "com.example.square"
        minSdk = Versions.Android.minSdkVersion
        targetSdk = Versions.Android.targetSdkVersion
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeVersion
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
}

dependencies {

    implementation(Deps.Android.X.core)
    implementation(Deps.Android.X.constraintLayout)
    implementation(Deps.Android.X.appCompat)
    implementation(Deps.Android.material)
    implementation(Deps.Navigation.navFragment)
    implementation(Deps.Navigation.navUI)
    implementation(Deps.Hilt.hiltAndroid)
    kapt(Deps.Hilt.hiltAndroidCompiler)
    implementation(Deps.Android.X.lifecycleRuntime)

    implementation(Deps.Navigation.navCompose)
    implementation(Deps.Compose.composeLiveData)
    implementation(Deps.Compose.composeMaterial)
    implementation(Deps.Compose.composeFoundation)
    implementation(Deps.Compose.composeActivity)
    implementation(Deps.Compose.composeUI)
    implementation(Deps.Compose.composeUiTool)
    implementation(Deps.Compose.composeUiTest)
    implementation(Deps.Compose.composePager)
    implementation(Deps.Compose.composePagerIndicators)

    implementation(Deps.Network.apacheCommon)

    implementation(Deps.Network.retrofit)
    implementation(Deps.Network.retrofitConverter)
    implementation(Deps.Network.retrofitRx)

    implementation(Deps.Network.okHttpLogging)
    implementation(Deps.Network.okHttpUrlConnection)

    implementation(Deps.Network.loopjAsync)

    implementation(Deps.Room.runtime)
    implementation(Deps.Room.ktx)
    kapt(Deps.Room.compiler)

    testImplementation(Deps.AndroidTest.junit)
    androidTestImplementation(Deps.AndroidTest.ext)
    androidTestImplementation(Deps.AndroidTest.Espresso.core)
}