plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("jacoco")
}

jacoco {
    toolVersion = "0.8.12" // Remplacez par la dernière version stable si nécessaire
}


android {
    namespace = "com.example.myapplicationjacoco"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplicationjacoco"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        // Instrumentation Runner pour debugunprotectede
    }

    buildTypes {

        debug {
            // Activer JaCoCo pour le build debug
            isTestCoverageEnabled = true
        }

        // Créer la variante debugunprotected
        create("debugunprotected") {
            initWith(buildTypes["debug"]) // Hériter des configurations de debug
            isTestCoverageEnabled = true
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    sourceSets {
        getByName("debugunprotected") {
            java.srcDirs("src/debugunprotected/java")
            res.srcDirs("src/debugunprotected/res")
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
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation("androidx.test:rules:1.2.0")



}

