# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# Keep TapsellPlus classes
-keep class ir.tapsell.plus.** { *; }
-dontwarn ir.tapsell.plus.**

# Keep Capacitor classes
-keep class com.getcapacitor.** { *; }
-dontwarn com.getcapacitor.**

# Keep plugin classes
-keep class com.capacitorcommunity.plugins.tapsellplus.** { *; }

