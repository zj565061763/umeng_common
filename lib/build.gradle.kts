plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    `maven-publish`
}

val libGroupId = "com.sd.lib.umeng"
val libArtifactId = "umeng_common"
val libVersionName = "9.5.2-v02"

android {
    namespace = "com.sd.lib.umeng.common"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        kotlinOptions.freeCompilerArgs += "-module-name=$libGroupId.$libArtifactId"
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    api(libs.umsdk.common)
    api(libs.umsdk.asms)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = libGroupId
                artifactId = libArtifactId
                version = libVersionName
            }
        }
    }
}