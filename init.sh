#!/bin/bash

# APPT2
sudo apt-get install aapt -y

# JDK
sudo apt-get install openjdk-8-jdk-headless

# SDKMANAGER
wget https://dl.google.com/android/repository/sdk-tools-linux-4333796.zip
unzip *.zip -d ~/android-sdk
sdkmanager "platforms;android-23"
sdkmanager "build-tools;28.0.1"

# GENERATE KEY
keytool -genkeypair -keystore mykey.keystore -storepass 123456 -keyalg RSA -keysize 2048




# R.java
aapt package -f -m -J src -M AndroidManifest.xml -S res -I ~/platforms/android-23/android.jar

# .class
mkdir bin
javac -cp src src/com/funky/*.java -bootclasspath ~/platforms/android-23/android.jar -d bin

# classes.dex
~/build-tools/28.0.1/dx --dex --output=classes.dex bin

# output.apk
aapt package -f -m -F output.apk -M AndroidManifest.xml -S res -I ~/platforms/android-23/android.jar
aapt add output.apk classes.dex

# KEYTOOL
~/build-tools/28.0.1/apksigner sign --ks mykey.keystore -ks-pass pass:123456 output.apk

# ADB
adb install -r output.apk
adb shell am start -n com.funkymsg/.MainActivity
