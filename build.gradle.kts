plugins {
    `kotlin-dsl`
}

group = "no.jluehr.buildlogic"

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.compose.gradlePlugin)
}