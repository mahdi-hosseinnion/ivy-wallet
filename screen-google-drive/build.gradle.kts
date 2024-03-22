plugins {
    id("ivy.feature")
}

android {
    namespace = "com.ivy.drive"
}

dependencies {
    implementation(projects.ivyBase)
    implementation(projects.ivyResources)
    implementation(projects.ivyDesign)
    implementation(projects.ivyDomain)
    implementation(projects.ivyNavigation)
    implementation(projects.ivyCommonUi)

    testImplementation(projects.ivyTesting)
}
