package com.steamtechs.renaissancelife.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MockBluetoothHandler

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RealBluetoothHandler

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MockBluetoothRepository

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RealBluetoothRepository