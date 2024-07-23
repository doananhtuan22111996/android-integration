# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keep class vn.root.app.** { *; }

# This is generated automatically by the Android Gradle plugin.
-dontwarn com.google.api.client.http.GenericUrl
-dontwarn com.google.api.client.http.HttpHeaders
-dontwarn com.google.api.client.http.HttpRequest
-dontwarn com.google.api.client.http.HttpRequestFactory
-dontwarn com.google.api.client.http.HttpResponse
-dontwarn com.google.api.client.http.HttpTransport
-dontwarn com.google.api.client.http.javanet.NetHttpTransport$Builder
-dontwarn com.google.api.client.http.javanet.NetHttpTransport
-dontwarn org.joda.time.Instant
-dontwarn hilt_aggregated_deps._vn_root_data_di_local_DaoModule
-dontwarn hilt_aggregated_deps._vn_root_data_di_local_LocalModule
-dontwarn hilt_aggregated_deps._vn_root_data_di_network_NetworkModule

# AndroidX Paging
-keep class androidx.paging.** { *; }
-dontwarn androidx.paging.**

# Retrofit
-keep class retrofit2.** { *; }
-dontwarn retrofit2.**

# OkHttp (used by Retrofit)
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**

# Gson
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**

# Keep Gson TypeAdapters and InstanceCreators
-keep class * implements com.google.gson.TypeAdapterFactory { *; }
-keep class * implements com.google.gson.InstanceCreator { *; }

# Keep specific classes used for JSON serialization/deserialization
-keep class vn.root.data.model.** { *; }

# Keep all annotations
-keepattributes *Annotation*

# Keep all public classes, methods, and fields
-keep public class * {
    public protected *;
}

# Keep classes used via reflection
-keep class vn.root.data.model.reflection.** { *; }

# Keep classes with specific methods
-keep class * {
    @com.google.gson.annotations.SerializedName <fields>;
    @com.google.gson.annotations.Expose <fields>;
}

# Keep classes with specific fields
-keep class * {
    @com.google.gson.annotations.SerializedName <methods>;
    @com.google.gson.annotations.Expose <methods>;
}