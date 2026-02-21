plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}
val baseGroup = "no.jluehr"
val pathNamespace = project.name
    .replace(":", ".")
    .replace(Regex("[^\\w.]"), "_")
    .trim('.')

android {
    compileSdk = 36

    namespace = "$baseGroup.$pathNamespace"

    defaultConfig {
        minSdk = 31
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        compose = true
    }
}
val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

// Helper function to make the syntax cleaner below
fun String.asLib() = libs.findLibrary(this).get()
fun String.asBundle() = libs.findBundle(this).get()

dependencies {
    // implementation(libs.bundles.base)
    "implementation"("base".asBundle())

    // implementation(platform(libs.androidx.compose.bom))
    "implementation"(platform("androidx-compose-bom".asLib()))

    // Note: hardware-integration is likely specific to this module,
    // but if you want it in the plugin, uncomment below:
    // "implementation"("hardware-integration".asBundle())

    // testImplementation(libs.junit)
    "testImplementation"("junit".asLib())

    // androidTestImplementation(platform(libs.androidx.compose.bom))
    "androidTestImplementation"(platform("androidx-compose-bom".asLib()))

    // androidTestImplementation(libs.bundles.testing)
    "androidTestImplementation"("testing".asBundle())

    // debugImplementation(libs.bundles.compose.debug)
    "debugImplementation"("compose-debug".asBundle())

   //"implementation"("tens-infrastructure".asLib())
}


