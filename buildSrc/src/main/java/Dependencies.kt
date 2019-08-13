object ApplicationId {
    val id = "com.rafael.fernandes.desafioconcrete"
}

object Modules {
    val app = ":app"
    val data = ":data"
    val domain = ":domain"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object Versions {
    val gradle = "3.2.1"
    val compileSdk = 28
    val minSdk = 21
    val targetSdk = 28

    //testes
    val junit = "4.12"
    val runner = " 1.0.2"
    val espresso = "3.0.2"
    val reules = "1.0.1"
    val roomTesting = "1.0.0-rc"
    val coreTesting = "1.0.0-rc1"
    val corexTesting = "2.0.1"
    val mockwebserver = "4.0.1"
    val mockito = "2.0.0"
    val assertCore = "3.11.1"
    val monitor = "1.1.0-alpha3"

    //kotlin
    val rxkotlin = "2.3.0"

    // supportLibraries
    val constraint_layout = "1.1.3"
    val appcompatx = "1.0.2"
    val appcompatxAlpha4 = "1.1.0-alpha04"
    //injections
    val javaxInject = "1"
    val javaxAnnotation = "1.3"
    // retrofit
    val retrofitVersion = "2.3.0"
    val rxandroid = "2.0.1"
    val okHttpVersion = "3.8.0"
    val interceptor = "3.4.1"
    val rxetrofitAdapter = "2.5.0"
    val retrofit = "2.5.0"

    //gson
    val gsonVersion = "2.8.1"

    // dagger
    val dagger = "2.14.1"

    //arch
    val lifecycle = "2.0.0"
    val design = "1.0.0"
    val coroutines = "1.3.0-RC2"

    // persistence
    val roomLifecycleVersion = "1.1.1"
    val roomRXVersion = "1.1.1"

    // presentation
    val glide = "4.8.0"
    val imagePickerVersion = "1.1"

}

object Libraries {
    val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    val javaxInject = "javax.inject:javax.inject:${Versions.javaxInject}"
    val javaxAnnotation = "javax.annotation:javax.annotation-api:${Versions.javaxAnnotation}"
    val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    val daggerAndroidCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
    val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"
    val rxjavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.rxetrofitAdapter}"
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    val roomLifecycle = "android.arch.persistence.room:runtime:${Versions.roomLifecycleVersion}"
    val roomCompiler = "android.arch.persistence.room:compiler:${Versions.roomLifecycleVersion}"
    val roomRX = "android.arch.persistence.room:rxjava2:${Versions.roomRXVersion}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    val imagePicker = "com.github.dhaval2404:imagepicker-support:${Versions.imagePickerVersion}"
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

}

object SupportLibraries {
    val appcompatx = "androidx.appcompat:appcompat:${Versions.appcompatx}"
    val appcompatxResources = "androidx.appcompat:appcompat-resources:${Versions.appcompatxAlpha4}"
    val design = "com.google.android.material:material:${Versions.design}"
}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
    val runner = "com.android.support.test:runner:${Versions.runner}"
    val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
    val testRules = "com.android.support.test:rules:${Versions.reules}"
    val roomTesting = "android.arch.persistence.room:testing:${Versions.roomTesting}"
    val androidCoreTesting = "android.arch.core:core-testing:${Versions.coreTesting}"
    val androidxCoreTesting = "androidx.arch.core:core-testing:${Versions.corexTesting}"
    val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.mockwebserver}"
    val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito}"
    val assertjCore = "org.assertj:assertj-core:${Versions.assertCore}"
    val monitor = "androidx.test:monitor:${Versions.monitor}"
}