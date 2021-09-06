package com.steamtechs.renaissancelife.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MockBluetoothHandler

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RealBluetoothHandler