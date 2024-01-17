plugins {
    id("ivy.feature")
}

android {
    namespace = "com.ivy.google_drive"

}

dependencies {
    implementation(projects.ivyBase)
    implementation(projects.ivyResources)
    implementation(projects.ivyDesign)
    implementation(projects.ivyDomain)
    implementation(projects.ivyNavigation)
    implementation(projects.ivyCommonUi)
    // Used for google sign in
    implementation(libs.google.playservices.auth)
    // Google drive integration
    implementation(libs.google.http.android)
    implementation(libs.google.services.drive)
    implementation(libs.google.http.gson)
    // Added to avoid duplicate class error for more information checkout: https://stackoverflow.com/q/56639529
    implementation(libs.listenablefutureExcluded)
    testImplementation(projects.ivyTesting)
}
