plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {

    compileSdk = 36
    defaultConfig {
        minSdk = 31
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    flavorDimensions += "environment"
    productFlavors {
        create("prod") { dimension = "environment" }
        create("mock") { dimension = "environment" }
    }

    buildFeatures {
        compose = true
    }
}
val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun String.asLib() = libs.findLibrary(this).get()
fun String.asBundle() = libs.findBundle(this).get()

dependencies {
    "implementation"("base".asBundle())

    "implementation"(platform("androidx-compose-bom".asLib()))
    "testImplementation"("junit".asLib())
    "androidTestImplementation"(platform("androidx-compose-bom".asLib()))
    "androidTestImplementation"("testing".asBundle())
    "debugImplementation"("compose-debug".asBundle())
    "implementation"("hardware-integration".asBundle())
}