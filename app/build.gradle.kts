plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.navigationSafeArgs)
    alias(libs.plugins.daggerHilt)
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {

    namespace = "com.desmond.rightmove"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.desmond.rightmove"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildFeatures.buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "RAPID_API_KEY", "\"${project.findProperty("RAPID_API_KEY")}\"")
            buildConfigField("String", "BASE_URL", "\"${project.findProperty("BASE_URL")}\"")
        }
        release {
            buildConfigField("String", "RAPID_API_KEY", "\"${project.findProperty("RAPID_API_KEY")}\"")
            buildConfigField("String", "BASE_URL", "\"${project.findProperty("BASE_URL")}\"")

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
    buildFeatures {
        compose = true
        dataBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    tasks.withType<Test> {
        useJUnitPlatform()
    }

    kapt {
        correctErrorTypes = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    testImplementation(libs.junit.jupiter)
    kapt(libs.dagger.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.ui.viewbinding)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.navigation.fragment.compose)
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.graphics.android)
    implementation(libs.activity.ktx)
    implementation(libs.fragment.ktx)
    implementation(libs.material)
    implementation(libs.nav.fragment.ktx)
    implementation(libs.nav.ui.ktx)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.okHttp)
    implementation(libs.okHttpLoggingInterceptor)
    implementation(libs.retrofit)
    implementation(libs.moshi)
    implementation(libs.moshiKotlin)
    implementation(libs.moshiConverter)

    testImplementation(libs.core.testing)
    implementation(libs.gson)
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.core.testing)
    testImplementation(libs.mockk)
    testImplementation(libs.mockk.android)
    testImplementation(libs.assertk)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.junit)
    testImplementation(libs.turbine)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(kotlin("script-runtime"))
    debugImplementation(libs.ui.tooling)
}