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

    implementation(libs.google.playservices.auth)
    implementation(libs.google.services.drive)
    //Google
    implementation(libs.google.http.gson)
    implementation(libs.google.http.android)
    implementation("com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava") //TODO Add it to versions
    testImplementation(projects.ivyTesting)
}
