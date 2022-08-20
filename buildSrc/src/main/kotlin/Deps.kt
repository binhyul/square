object Deps {
    object Android {
        object X {
            const val core = "androidx.core:core-ktx:${Versions.coreVersion}"
            const val lifecycleRuntime =
                "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"
            const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"


            const val constraintLayout =
                "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
        }

        const val material = "com.google.android.material:material:${Versions.materialVersion}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        const val retrofitConverter =
            "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
        const val retrofitRx = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"

        const val okHttpLogging =
            "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"
        const val okHttpUrlConnection =
            "com.squareup.okhttp3:okhttp-urlconnection:${Versions.okHttpVersion}"
        const val loopjAsync = "com.loopj.android:android-async-http:${Versions.loopjAsyncVersion}"
        const val apacheCommon = "org.apache.commons:commons-lang3:${Versions.apacheCommonVersion}"
    }

    object AndroidTest {
        const val junit = "junit:junit:${Versions.AndroidTest.junitVersion}"
        const val core = "androidx.test:core:${Versions.AndroidTest.coreVersion}"
        const val runner = "androidx.test:runner:${Versions.AndroidTest.runnerVersion}"
        const val rules = "androidx.test:rules:${Versions.AndroidTest.rulesVersion}"
        const val ext = "androidx.test.ext:junit-ktx:${Versions.AndroidTest.extVersion}"

        object Espresso {
            const val core =
                "androidx.test.espresso:espresso-core:${Versions.AndroidTest.espressoVersion}"
        }
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.roomVersion}"
        const val ktx = "androidx.room:room-ktx:${Versions.roomVersion}"
        const val compiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    }

    object Hilt {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.Hilt.hiltAndroidVersion}"
        const val hiltAndroidCompiler =
            "com.google.dagger:hilt-android-compiler:${Versions.Hilt.hiltAndroidVersion}"
    }

    object Navigation {
        const val navFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
        const val navUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
        const val navCompose = "androidx.navigation:navigation-compose:${Versions.navigationVersion}"
    }

    object Compose {
        const val composeUI = "androidx.compose.ui:ui:${Versions.composeVersion}"
        const val composeUiTool = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
        const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.composeVersion}"
        const val composeMaterial = "androidx.compose.material:material:${Versions.composeVersion}"
        const val composeIconCore = "androidx.compose.material:material-icons-core:${Versions.composeVersion}"
        const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
        const val composeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.composeVersion}"
        const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}"

        const val composePager = "com.google.accompanist:accompanist-pager:${Versions.composePager}"
        const val composePagerIndicators = "com.google.accompanist:accompanist-pager-indicators:${Versions.composePager}"
    }

}