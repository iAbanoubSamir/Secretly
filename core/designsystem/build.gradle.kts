plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.android.secretly.core.designsystem"
    compileSdk = libs.versions.compile.sdk.version.get().toInt()

    defaultConfig {
        minSdk = libs.versions.min.sdk.version.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.ui.text.google.fonts)

    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.tooling.preview)
    api(libs.androidx.material3)

    implementation(libs.coil.compose)
}